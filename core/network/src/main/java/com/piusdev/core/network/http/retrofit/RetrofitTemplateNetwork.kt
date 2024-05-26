package com.piusdev.core.network.http.retrofit

import androidx.tracing.trace
import com.piusdev.core.network.BuildConfig
import com.piusdev.core.network.http.ApiService
import com.piusdev.core.network.http.model.VesselSearchResponse
import javax.inject.Inject
import javax.inject.Singleton

private const val API_URL = BuildConfig.API_URL


@Singleton
internal class RetrofitTemplateNetwork @Inject constructor(
    apiService: ApiService
): TemplateNetworkDataSource {
    private val retrofitApi = trace("ApiService") { apiService }

    override suspend fun getSearchVessel(mmsi: String?): VesselSearchResponse =
        retrofitApi.searchVessels(mmsi = mmsi)
}