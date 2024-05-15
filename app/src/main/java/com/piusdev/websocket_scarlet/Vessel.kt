package com.piusdev.websocket_scarlet

data class Vessel(
    val mmsi: String,
    val timestamp: Long,
    val lat: Float?,
    val lon: Float?,
)
