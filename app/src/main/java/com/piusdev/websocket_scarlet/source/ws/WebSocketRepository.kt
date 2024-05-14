package com.piusdev.websocket_scarlet.source.ws

import android.util.Log
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WebSocketRepository @Inject constructor(private val webSocketService: WsService) {
    fun startListening() = flow {
        webSocketService.observeMessages().collect {
            Log.d("WebSocketRepository", "Received message: $it")
            emit(it)
        }
    }

    fun observeEvents() = webSocketService.observeEvents()

    fun sendLatLon(latLon: String) = webSocketService.sendLatLon(latLon)
}