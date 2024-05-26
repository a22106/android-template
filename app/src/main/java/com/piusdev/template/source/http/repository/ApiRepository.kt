package com.piusdev.template.source.http.repository

import com.piusdev.template.source.http.ApiService
import com.piusdev.template.source.http.VesselSearchResponse
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface ApiRepository {
    fun getVesselsSearch(): Flow<Result<VesselSearchResponse>>
}
