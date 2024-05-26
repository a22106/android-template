package com.piusdev.core.network.http.di

import androidx.tracing.trace
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.piusdev.core.network.BuildConfig
import com.piusdev.core.network.http.ApiService
import com.piusdev.core.network.http.repository.ApiRepository
import com.piusdev.core.network.http.repository.ApiRepositoryImpl
import com.piusdev.core.network.ws.di.AppModule_ProvideOkhttpClientFactory
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Call
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

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
    fun provideRetrofit(
        networkJson: Json,
        okHttpClient: OkHttpClient,
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