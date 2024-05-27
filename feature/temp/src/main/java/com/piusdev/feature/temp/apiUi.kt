package com.piusdev.feature.temp

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel


@Composable
fun WebSocketTest(viewModel: WsViewModel) {
    Box(modifier = Modifier.padding(8.dp)) {
        Column {
            Button(onClick = { viewModel.startListening() }) {
                Text("Start Listening")
            }
            val messages by viewModel.messages.collectAsState()
            Text(text = messages)
            Spacer(modifier = Modifier.padding(8.dp))
            Button(onClick = { viewModel.send("0,0") }) {
                Text("0,0")
            }
            // api request and response
            Button(onClick = {
                viewModel.getVessels(
                    apiToken = "Bearer dev_token",
                    mmsi = "261000106"
                )
            }) {
                Text("Get Vessels")
            }
            val apiState by viewModel.apiState.collectAsState()
            when (apiState) {
                is ApiUiState.Loading -> Text(text = "Loading...")
                is ApiUiState.Success -> {
                    val vessels = (apiState as ApiUiState.Success).data.content.vessels
                    Text(text = vessels.getOrNull(0)?.shipname ?: "No Data")
                }
                is ApiUiState.Error -> Text(text = (apiState as ApiUiState.Error).message)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    val viewModel: WsViewModel = hiltViewModel()
    WebSocketTest(viewModel)
}