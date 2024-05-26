package com.piusdev.core.network.http.model

import kotlinx.serialization.Serializable

@Serializable
data class VesselSearchResponse(
    val success: Boolean,
    val content: Content,
    val response: String
)

@Serializable
data class Content(
    val vessels: List<Vessel>,
    val number: Int,
    val totalPages: Int,
    val totalElements: Int,
    val numberOfElement: Int,
    val first: Boolean,
    val last: Boolean,
    val empty: Boolean
)

@Serializable
data class Vessel(
    val my_fleet: Boolean,
    val mmsi: String,
    val timestamp: Long,
    val lat: Double,
    val lon: Double,
    val course: Double,
    val speed: Double,
    val heading: Int,
    val imo: Int?,
    val status: Int,
    val shipname: String,
    val callsign: String?,
    val shiptype: Int?,
    val to_bow: Int,
    val to_stern: Int,
    val to_port: Int,
    val to_starboard: Int,
    val draught: Double,
    val destination: String?,
    val eta_ais: Long?,
    val eta: Long?,
    val source: String,
    val eta_timestamp: Long?
)