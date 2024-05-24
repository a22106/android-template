package com.piusdev.template.di

import com.piusdev.template.source.ws.WebSocketRepository
import com.piusdev.template.source.ws.WebSocketRepositoryImpl
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
    abstract fun bindsRepository(impl: WebSocketRepositoryImpl): WebSocketRepository

}