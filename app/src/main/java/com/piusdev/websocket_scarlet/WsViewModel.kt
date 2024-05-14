package com.piusdev.websocket_scarlet

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.piusdev.websocket_scarlet.source.ws.WebSocketRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WsViewModel @Inject constructor(private val webSocketRepository: WebSocketRepository): ViewModel() {
    fun startListening() {
        viewModelScope.launch {
            webSocketRepository.startListening()
        }
    }
}