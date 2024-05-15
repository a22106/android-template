package com.piusdev.websocket_scarlet.phillip.ws

import com.google.gson.Gson
import com.google.gson.JsonParser
import com.piusdev.websocket_scarlet.phillip.ws.model.AisMessageResponse
import com.piusdev.websocket_scarlet.phillip.ws.model.WsRequestModel
import com.tinder.scarlet.Message
import com.tinder.scarlet.MessageAdapter
import java.lang.reflect.Type

@Suppress("UNCHECKED_CAST")
class CustomGsonMessageAdapter<T> private constructor(
    val gson: Gson
) : MessageAdapter<T> {
    // 웹소켓에서 받은 메시지를 T 타입으로 변환
    override fun fromMessage(message: Message): T {
        val stringValue = when (message) {
            is Message.Text -> message.value
            is Message.Bytes -> message.value.toString()
        }

        val jsonObject = JsonParser.parseString(stringValue).asJsonObject
        val type = AisMessageResponse::class.java

        val obj = gson.fromJson(stringValue, type)
        return obj as T
    }

    // T 타입을 웹소켓으로 보낼 메시지로 변환
    override fun toMessage(data: T): Message { // toMessage is for sending data to the WebSocket Stream
        if (data is WsRequestModel) {
            val latLonString = "${data.lat},${data.lon}"
            return Message.Text(latLonString)
        }
        return Message.Text("") // Return empty message if data is not WsRequestModel
    }

    class Factory(
        private val gson: Gson
    ): MessageAdapter.Factory {
        override fun create(type: Type, annotations: Array<Annotation>): MessageAdapter<*> {
            return CustomGsonMessageAdapter<Any>(gson)
        }
    }
}
