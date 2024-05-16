package com.piusdev.websocket_scarlet.source.ws

import com.piusdev.websocket_scarlet.source.ws.model.AisMessage
import com.tinder.scarlet.WebSocket
import com.tinder.scarlet.ws.Receive
import com.tinder.scarlet.ws.Send
import io.reactivex.Flowable
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.flow.Flow

interface WsService {
    @Receive
    fun observeEvents(): Flow<WebSocket.Event>

    @Send
    fun sendLatLon(latLon: String): Boolean

    @Receive
    fun observeMessages(): Flow<AisMessage>
}