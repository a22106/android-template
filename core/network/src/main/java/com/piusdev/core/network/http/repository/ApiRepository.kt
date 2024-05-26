package com.piusdev.core.network.http.repository

import com.piusdev.core.network.http.model.VesselSearchResponse
import kotlinx.coroutines.flow.Flow

interface ApiRepository {
    fun getVesselsSearch(): Flow<Result<VesselSearchResponse>>
}
