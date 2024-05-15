package com.piusdev.websocket_scarlet.phillip.ws

import com.piusdev.websocket_scarlet.phillip.ws.model.AisMessageResponse
import com.piusdev.websocket_scarlet.phillip.ws.model.BaseModel
import com.tinder.scarlet.WebSocket
import com.tinder.scarlet.ws.Receive
import com.tinder.scarlet.ws.Send
import kotlinx.coroutines.flow.Flow

interface WsApi {
    @Receive
    fun observeEvents(): Flow<WebSocket.Event>

    @Send
    fun sendBaseModel(baseModel: BaseModel): Boolean

    @Receive
    fun observeBaseModels(): Flow<AisMessageResponse>
}