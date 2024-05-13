package com.piusdev.websocket_scarlet

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class WsApplication: Application() {
    override fun onCreate() {
        super.onCreate()
    }
}
