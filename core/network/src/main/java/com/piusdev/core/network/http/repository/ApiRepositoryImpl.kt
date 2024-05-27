package com.piusdev.core.network.http.repository

import com.piusdev.core.network.http.ApiService
import com.piusdev.core.network.http.model.VesselSearchResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ApiRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : ApiRepository {
    override fun getVesselsSearch(
        apiToken: String,
        mmsi: String?,
        imo: String?,
        shipname: String?,
        callsign: String?,
        format: String?,
        pageNumber: Int?,
        pageSize: Int?,
        userId: String?
    ): Flow<Result<VesselSearchResponse>> = flow {
        val response = apiService.searchVessels(
            apiToken = apiToken,
            mmsi = mmsi,
            imo = imo,
            shipname = shipname,
            callsign = callsign,
            format = format,
            pageNumber = pageNumber,
            pageSize = pageSize,
            userId = userId
        )
        emit(Result.success(response))
    }.catch {
        emit(Result.failure(it))
    }
}