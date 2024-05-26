package com.piusdev.core.network.di

import com.piusdev.core.network.ws.WebSocketRepository
import com.piusdev.core.network.ws.WebSocketRepositoryImpl
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