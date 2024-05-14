package com.piusdev.websocket_scarlet.di

import android.app.Application
import android.content.Context
import com.google.gson.Gson
import com.piusdev.websocket_scarlet.source.ws.CustomGsonMessageAdapter
import com.piusdev.websocket_scarlet.source.ws.FlowStreamAdapter
import com.piusdev.websocket_scarlet.source.ws.WsService
import com.piusdev.websocket_scarlet.util.DispatcherProvider
import com.tinder.scarlet.Scarlet
import com.tinder.scarlet.lifecycle.android.AndroidLifecycle
import com.tinder.scarlet.retry.LinearBackoffStrategy
import com.tinder.scarlet.websocket.okhttp.newWebSocketFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ActivityRetainedScoped
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
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
    fun provideOkhttpClient(): OkHttpClient{
        return OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY })
            .build()
    }

    @Provides
    @Singleton
    fun provideWebSocketService(scarlet: Scarlet): WsService {
        return scarlet.create(WsService::class.java)
    }

    @Singleton
    @Provides
    fun providesWsApi(
        app: Application,
        okHttpClient: OkHttpClient,
    ): Scarlet {
        return Scarlet.Builder()
            .webSocketFactory(okHttpClient.newWebSocketFactory("wss://ais-websocket-broadcaster-dev-srvcu3razq-du.a.run.app/"))
            .lifecycle(AndroidLifecycle.ofApplicationForeground(app))
            .build()
    }
}