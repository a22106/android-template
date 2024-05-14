package com.piusdev.websocket_scarlet.source.ws

import android.util.Log
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WebSocketRepository @Inject constructor(private val webSocketService: WsService) {
    suspend fun startListening(){
        webSocketService.observeMessages().collect {
            Log.d("WebSocketRepository", "startListening: $it")
        }
    }

    fun observeEvents() = webSocketService.observeEvents()

    fun sendLatLon(latLon: String) = webSocketService.sendLatLon(latLon)

}