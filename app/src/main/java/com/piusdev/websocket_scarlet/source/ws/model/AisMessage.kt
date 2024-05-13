package com.piusdev.websocket_scarlet.source.ws.model

data class AisMessage (
    val mmsi: Long,
    val timestamp: Long,
    val ais_nmea: List<String>,
    val ais_decoded: AisDecoded,
    val source: String
)

data class AisDecoded(
    val mmsi: Long,
    val status: Int?,
    val lon: Float?,
    val lat: Float?,
    val speed: Float?,
    val course: Float?,
    val callsign: String?,
    val draught: Float?,
    val shipname: String,
    val to_bow: Float?,
    val to_stern: Float?,
    val to_port: Float?,
    val to_starboard: Float?
)