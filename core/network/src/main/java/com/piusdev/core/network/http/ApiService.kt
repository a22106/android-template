@file:Suppress("PLUGIN_IS_NOT_ENABLED")

package com.piusdev.core.network.http

import com.piusdev.core.network.http.model.VesselSearchResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET(value = "v1/vessels/search")
    suspend fun searchVessels(
        @Query("mmsi") mmsi: String?= null,
        @Query("imo") imo: Int? = null,
        @Query("shipname") shipname: String?= null,
        @Query("callsign") callsign: String?= null,
        @Query("format") format: String?= null,
        @Query("pageNumber") pageNumber: Int?= null,
        @Query("pageSize") pageSize: Int?= null,
        @Query("user_id") userId: String?= null
    ): VesselSearchResponse
}
