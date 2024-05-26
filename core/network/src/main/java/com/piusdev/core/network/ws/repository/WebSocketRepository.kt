package com.piusdev.core.network.ws.repository

import com.piusdev.core.network.ws.model.AisMessage
import com.tinder.scarlet.WebSocket
import kotlinx.coroutines.flow.Flow

interface WebSocketRepository {

    fun startListening(): Flow<AisMessage>

    fun observeEvents(): Flow<WebSocket.Event>

    fun sendLatLon(latLon: String): Boolean

}