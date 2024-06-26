package com.piusdev.core.network.http.di

import android.util.Log
import androidx.tracing.trace
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.piusdev.core.network.BuildConfig
import com.piusdev.core.network.http.ApiService
import com.piusdev.core.network.http.model.ApiErrorResponse
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Call
import okhttp3.Interceptor
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.HttpException
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

class ApiErrorInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val response = chain.proceed(chain.request())
        if (!response.isSuccessful) {
            response.body?.let { responseBody ->
                val errorBody = responseBody.string()
                val apiErrorResponse = try {
                    Json.decodeFromString<ApiErrorResponse>(errorBody)
                } catch (e: Exception) {
                    ApiErrorResponse(
                        statusCode = response.code,
                        description = "Unknown Error",
                        message = "Unknown Error",
                        code = "unknown_error"
                    )
                }
                throw ApiException(apiErrorResponse)
            }
        }
        return response
    }
}

class ApiException(val errorResponse: ApiErrorResponse) : Exception(errorResponse.description)

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {
    @Provides
    @Singleton
    fun providesNetworkJson(): Json = Json {
        ignoreUnknownKeys = true
    }

    @Provides
    @Singleton
    fun okhttpCallFactory(): Call.Factory = trace("TemplateOkHttpClient") {
        OkHttpClient.Builder()
            .addInterceptor(
                HttpLoggingInterceptor()
                    .apply {
                        if (BuildConfig.DEBUG) {
                            setLevel(HttpLoggingInterceptor.Level.BODY)
                        }
                    },
            )
            .build()
    }

    @Provides
    @Singleton
    @Named("api")
    fun provideOkHttpClient(): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor().apply {
            level = if (BuildConfig.DEBUG) {
                HttpLoggingInterceptor.Level.BODY
            } else {
                HttpLoggingInterceptor.Level.NONE
            }
        }

        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(
        networkJson: Json,
        @Named("api") okHttpClient: OkHttpClient,
        okhttpClientFactory: dagger.Lazy<Call.Factory>
    ): ApiService {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.API_URL)
            .client(okHttpClient)
            .addConverterFactory(
                networkJson.asConverterFactory("application/json".toMediaType()))
            .callFactory(okhttpClientFactory.get())
            .build()
            .create(ApiService::class.java)
    }
}