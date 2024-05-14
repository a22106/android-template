package com.piusdev.websocket_scarlet.source.ws

import com.google.gson.Gson
import com.google.gson.JsonParser
import com.piusdev.websocket_scarlet.source.ws.model.AisMessage
import com.piusdev.websocket_scarlet.source.ws.model.WsRequestModel
import com.tinder.scarlet.Message
import com.tinder.scarlet.MessageAdapter
import java.lang.reflect.Type

@Suppress("UNCHECKED_CAST")
class CustomGsonMessageAdapter<T> private constructor(
    val gson: Gson
) : MessageAdapter<T> {
    override fun fromMessage(message: Message): T {
        val stringValue = when (message) { // Message is from the WebSocket Stream
            is Message.Text -> message.value
            is Message.Bytes -> message.value.toString()
        }

        val jsonObject = JsonParser.parseString(stringValue).asJsonObject
        val type = AisMessage::class.java

        val obj = gson.fromJson(stringValue, type)
        return obj as T
    }

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

fun main(){
    val customGsonMessageAdapter = CustomGsonMessageAdapter.Factory(Gson())
    val messageAdapter = customGsonMessageAdapter.create(AisMessage::class.java, arrayOf())
    val message = messageAdapter.fromMessage(Message.Text("{\"mmsi\": 123456789}"))
}