package com.piusdev.core.network.http.retrofit

import com.piusdev.core.network.http.model.VesselSearchResponse

interface TemplateNetworkDataSource {
    suspend fun getSearchVessel(mmsi: String?): VesselSearchResponse
}
