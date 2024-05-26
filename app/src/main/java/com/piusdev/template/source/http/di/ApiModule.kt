package com.piusdev.template.source.http.di

import com.piusdev.template.source.http.ApiService
import com.piusdev.template.source.http.repository.ApiRepository
import com.piusdev.template.source.http.repository.ApiRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class ApiModule {
    @Binds
    @Singleton
    abstract fun bindApiRepository(
        apiRepositoryImpl: ApiRepositoryImpl
    ): ApiRepository

    companion object{
        @Provides
        @Singleton
        fun provideRetrofit(): Retrofit{
            return Retrofit.Builder()
                .baseUrl("https://api.mapsea.io/maritime-dev/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }

        @Provides
        @Singleton
        fun provideApiService(retrofit: Retrofit): ApiService{
            return retrofit.create(ApiService::class.java)
        }
    }
}