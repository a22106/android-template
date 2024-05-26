package com.piusdev.core.network.ws.di

import com.piusdev.core.network.ws.repository.WebSocketRepository
import com.piusdev.core.network.ws.repository.WebSocketRepositoryImpl
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
        impl: WebSocketRepositoryImpl
    ): WebSocketRepository
}