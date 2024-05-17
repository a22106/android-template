package com.piusdev.websocket_scarlet.source.ws.model

import com.google.gson.annotations.SerializedName

data class AisMessage (
    @SerializedName("mmsi")
    val mmsi: Long,
    @SerializedName("timestamp")
    val timestamp: Long,
    @SerializedName("ais_nmea")
    val aisNmea: List<String>,
    @SerializedName("ais_decoded")
    val aisDecoded: AisDecoded,
    @SerializedName("source")
    val source: String
)

data class AisDecoded(
    @SerializedName("mmsi")
    val mmsi: Long,
    @SerializedName("status")
    val status: Int?,
    @SerializedName("lon")
    val lon: Float?,
    @SerializedName("lat")
    val lat: Float?,
    @SerializedName("speed")
    val speed: Float?,
    @SerializedName("course")
    val course: Float?,
    @SerializedName("callsign")
    val callSign: String?,
    @SerializedName("draught")
    val draught: Float?,
    @SerializedName("shipname")
    val shipName: String,
    @SerializedName("to_bow")
    val toBow: Float?,
    @SerializedName("to_stern")
    val toStern: Float?,
    @SerializedName("to_port")
    val toPort: Float?,
    @SerializedName("to_starboard")
    val toStarboard: Float?
)