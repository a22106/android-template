package com.piusdev.core.network.http.di

import com.piusdev.core.network.http.repository.ApiRepository
import com.piusdev.core.network.http.repository.ApiRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun bindsRepository(
        impl: ApiRepositoryImpl
    ): ApiRepository
}