package com.piusdev.core.network.ws.repository

import com.piusdev.core.network.ws.WsService
import javax.inject.Inject

class WebSocketRepositoryImpl @Inject constructor(
    private val webSocketService: WsService
) : WebSocketRepository {

    override fun startListening() = webSocketService.observeMessages()

    override fun observeEvents() = webSocketService.observeEvents()

    override fun sendLatLon(latLon: String) = webSocketService.sendLatLon(latLon)
}