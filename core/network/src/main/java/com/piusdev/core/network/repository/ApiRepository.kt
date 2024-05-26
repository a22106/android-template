package com.piusdev.core.network.repository

import com.piusdev.core.network.VesselSearchResponse
import kotlinx.coroutines.flow.Flow

interface ApiRepository {
    fun getVesselsSearch(): Flow<Result<VesselSearchResponse>>
}
