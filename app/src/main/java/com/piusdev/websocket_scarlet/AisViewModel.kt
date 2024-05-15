package com.piusdev.websocket_scarlet

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.piusdev.websocket_scarlet.phillip.util.DispatcherProvider
import com.piusdev.websocket_scarlet.phillip.ws.WsApi
import com.piusdev.websocket_scarlet.phillip.ws.model.AisMessageResponse
import com.piusdev.websocket_scarlet.phillip.ws.model.BaseModel
import com.tinder.scarlet.WebSocket
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AisViewModel @Inject constructor(
    private val wsApi: WsApi,
    private val dispatchers: DispatcherProvider,
    private val gson: Gson,
): ViewModel() {
//    private val _selectedColorButtonId = MutableStateFlow(R.id.rbBlack)
//    val selectedColorButtonId: StateFlow<Int> = _selectedColorButtonId
//
//    fun checkRadioButton(id: Int) {
//        _selectedColorButtonId.value = id
//    }
    // _vessel is a MutableStateFlow that holds a Map of Vessel objects. key is the MMSI number of the vessel
    private val _vessels = MutableStateFlow<Map<String, Vessel>>(emptyMap())
    val vessels = _vessels

    fun updateVessel(vessel: Vessel) {
        _vessels.value += (vessel.mmsi to vessel)
    }

    sealed class SocketEvent {
        data class Message(val message: String) : SocketEvent()
        data class Error(val error: Throwable) : SocketEvent()
        data class Closed(val code: Int, val reason: String) : SocketEvent()
    }

    private val connectionEventChannel = Channel<WebSocket.Event>()
    val connectionEvent = connectionEventChannel.receiveAsFlow().flowOn(dispatchers.io)

    private val socketEventsChannel = Channel<WebSocket.Event>()
    val socketEvents = socketEventsChannel.receiveAsFlow().flowOn(dispatchers.io)

    fun observeWsResponse(){
        viewModelScope.launch(dispatchers.io) {
            wsApi.observeBaseModels().collect {data ->
                when(data) {
                    is AisMessageResponse -> {
                        val vessel = Vessel(
                            mmsi = data.mmsi,
                            lat = data.ais_decoded.lat,
                            lon = data.ais_decoded.lon,
                            timestamp = data.timestamp
                        )
                        updateVessel(vessel)
                    }
                    else -> {
                        println("Unknown data type")
                    }
                }
            }
        }
    }

    fun sendBaseModel(data: BaseModel){
        viewModelScope.launch(dispatchers.io) {
            wsApi.sendBaseModel(data)
        }
    }
}