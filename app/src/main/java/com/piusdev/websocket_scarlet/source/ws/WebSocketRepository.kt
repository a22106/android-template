package com.piusdev.websocket_scarlet.source.ws

import com.piusdev.websocket_scarlet.source.ws.model.AisMessage
import com.tinder.scarlet.WebSocket
import kotlinx.coroutines.flow.Flow

interface WebSocketRepository {

    fun startListening(): Flow<AisMessage>

    fun observeEvents(): Flow<WebSocket.Event>

    fun sendLatLon(latLon: String): Boolean

}