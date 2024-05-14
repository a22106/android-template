package com.piusdev.websocket_scarlet.source.ws

import com.tinder.scarlet.WebSocket
import com.tinder.scarlet.ws.Receive
import com.tinder.scarlet.ws.Send
import kotlinx.coroutines.flow.Flow

interface WsService {
    @Receive
    fun observeEvents(): Flow<WebSocket.Event>

    @Send
    fun sendLatLon(latLon: String): Boolean

    @Receive
    fun observeMessages(): Flow<String>
}