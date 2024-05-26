package com.piusdev.template

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.piusdev.core.network.repository.ApiRepository
import com.piusdev.core.network.ws.WebSocketRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WsViewModel @Inject constructor(
    private val webSocketRepository: WebSocketRepository,
    private val apiRepository: ApiRepository,
) : ViewModel() {

    private val _messages = MutableStateFlow("No Message")
    val messages: StateFlow<String> = _messages

    private val _apiResponse = MutableStateFlow<String?>(null)
    val apiResponse: StateFlow<String?> = _apiResponse

    // get data from api
    fun getVessels() {
        viewModelScope.launch {
            apiRepository.getVesselsSearch().collect {result ->
                result.onSuccess {
                    _apiResponse.value = it.content.vessels.toString()
                }.onFailure {
                    _apiResponse.value = "Error: ${it.message}"
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