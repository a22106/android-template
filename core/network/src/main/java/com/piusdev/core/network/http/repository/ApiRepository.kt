package com.piusdev.core.network.http.repository

import com.piusdev.core.network.http.model.VesselSearchResponse
import kotlinx.coroutines.flow.Flow

interface ApiRepository {
    fun getVesselsSearch(
        apiToken: String,
        mmsi: String? = null,
        imo: String? = null,
        shipname: String? = null,
        callsign: String? = null,
        format: String? = null,
        pageNumber: Int? = null,
        pageSize: Int? = null,
        userId: String? = null
    ): Flow<Result<VesselSearchResponse>>
}
