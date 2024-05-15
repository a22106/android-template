package com.piusdev.websocket_scarlet.phillip.di

import android.app.Application
import android.content.Context
import com.google.gson.Gson
import com.piusdev.websocket_scarlet.phillip.ws.CustomGsonMessageAdapter
import com.piusdev.websocket_scarlet.phillip.ws.FlowStreamAdapter
import com.piusdev.websocket_scarlet.phillip.ws.WsApi
import com.piusdev.websocket_scarlet.phillip.util.DispatcherProvider
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
import kotlinx.coroutines.runBlocking
import okhttp3.OkHttpClient
import javax.inject.Singleton

@ExperimentalCoroutinesApi
@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    @ActivityRetainedScoped
    fun providesWsApi(
        app: Application,
        okHttpClient: OkHttpClient,
        gson: Gson
    ): WsApi {
        return Scarlet.Builder()
            .backoffStrategy(LinearBackoffStrategy(1000)) // convert 1000 to Constant
            .lifecycle(AndroidLifecycle.ofApplicationForeground(app))
            .webSocketFactory(okHttpClient.newWebSocketFactory("wss://ais-websocket-broadcaster-dev-srvcu3razq-du.a.run.app/"))
            .addStreamAdapterFactory(FlowStreamAdapter.Factory)
            .addMessageAdapterFactory(CustomGsonMessageAdapter.Factory(gson))
            .build()
            .create()
    }

    @Singleton
    @Provides
    fun provideApplicationContext(
        @ApplicationContext context: Context
    ) = context

    @Singleton
    @Provides
    fun provideGsonInstance(): Gson {
        return Gson()
    }

    @Singleton
    @Provides
    fun provideDispatcherProvider(): DispatcherProvider {
        return object : DispatcherProvider {
            override val main: CoroutineDispatcher
                get() = Dispatchers.Main
            override val io: CoroutineDispatcher
                get() = Dispatchers.IO
            override val default: CoroutineDispatcher
                get() = Dispatchers.Default
        }
    }
}