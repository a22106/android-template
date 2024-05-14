package com.piusdev.websocket_scarlet

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.piusdev.websocket_scarlet.source.ws.WebSocketRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WsViewModel @Inject constructor(private val webSocketRepository: WebSocketRepository): ViewModel() {
    private val _messages = MutableStateFlow("")
    val messages: StateFlow<String> = _messages

    fun startListening() {
        viewModelScope.launch {
            webSocketRepository.startListening().collect {
                Log.d("WsViewModel", "Received message: $it")
                _messages.value = it
            }
        }
    }
}