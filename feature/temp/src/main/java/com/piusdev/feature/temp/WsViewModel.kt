package com.piusdev.feature.temp

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.piusdev.core.network.http.model.VesselSearchResponse
import com.piusdev.core.network.http.repository.ApiRepository
import com.piusdev.core.network.ws.repository.WebSocketRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed interface ApiUiState {
    object Loading : ApiUiState
    data class Success(val data: VesselSearchResponse) : ApiUiState
    data class Error(val message: String) : ApiUiState
}

@HiltViewModel
class WsViewModel @Inject constructor(
    private val webSocketRepository: WebSocketRepository,
    private val apiRepository: ApiRepository,
) : ViewModel() {

    private val _messages = MutableStateFlow("No Message")
    val messages: StateFlow<String> = _messages

    private val _apiState = MutableStateFlow<ApiUiState>(ApiUiState.Loading)
    val apiState: StateFlow<ApiUiState> = _apiState

    // get data from api
    fun getVessels(
        apiToken: String,
        mmsi: String? = null,
        imo: String? = null,
        shipname: String? = null,
        callsign: String? = null) {
        viewModelScope.launch {
            _apiState.value = ApiUiState.Loading
            apiRepository.getVesselsSearch(
                apiToken = apiToken,
                mmsi = mmsi,
                imo = imo,
                shipname = shipname,
                callsign = callsign
            ).collect { result ->
                result.onSuccess {
                    Log.d("WsViewModel", "getVessels success: ${it.content.vessels}")
                    _apiState.value = ApiUiState.Success(it)
                }.onFailure {
                    Log.e("WsViewModel", "getVessels error", it)
                    _apiState.value = ApiUiState.Error(it.message ?: "Unknown Error")
                }
            }
        }
    }

    // start websocket connection
    fun startListening() {
        viewModelScope.launch {
            webSocketRepository.startListening().collect {
                Log.d("WsViewModel", "message=$it")
                _messages.value = it.toString()
            }
        }
    }

    fun send(latlon: String) {
        val result = webSocketRepository.sendLatLon(latlon)
        Log.d("WsViewModel", "send result=$result")
    }
}