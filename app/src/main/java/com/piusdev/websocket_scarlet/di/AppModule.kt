package com.piusdev.websocket_scarlet.di

import android.app.Application
import com.google.gson.Gson
import com.piusdev.websocket_scarlet.source.ws.FlowStreamAdapter
import com.piusdev.websocket_scarlet.source.ws.WsService
import com.tinder.scarlet.Scarlet
import com.tinder.scarlet.messageadapter.gson.GsonMessageAdapter
import com.tinder.scarlet.retry.LinearBackoffStrategy
import com.tinder.scarlet.websocket.okhttp.newWebSocketFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.ExperimentalCoroutinesApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideGsonInstance(): Gson {
        return Gson()
    }

    @Provides
    @Singleton
    fun provideOkhttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY })
            .build()
    }

    @Provides
    @Singleton
    fun provideWebSocketService(scarlet: Scarlet): WsService {
        return scarlet.create<WsService>()
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Singleton
    @Provides
    fun providesWsApi(okHttpClient: OkHttpClient): Scarlet {
        return Scarlet.Builder()
//            .lifecycle(AndroidLifecycle.ofApplicationForeground(app))
            .webSocketFactory(okHttpClient.newWebSocketFactory("wss://ais-websocket-broadcaster-dev-srvcu3razq-du.a.run.app/"))
            .backoffStrategy(LinearBackoffStrategy(1000))
            .addStreamAdapterFactory(FlowStreamAdapter.Factory)
            .addMessageAdapterFactory(GsonMessageAdapter.Factory())
            .build()
    }
}