package com.piusdev.template

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.piusdev.template.source.ws.WebSocketRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WsViewModel @Inject constructor(
    private val webSocketRepository: WebSocketRepositoryImpl,
) : ViewModel() {

    private val _messages = MutableStateFlow("No Message")
    val messages: StateFlow<String> = _messages

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