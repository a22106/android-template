package com.piusdev.websocket_scarlet

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.piusdev.websocket_scarlet.ui.theme.WebsocketscarletTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WsActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            WebsocketscarletTheme {
                WebSocketApp()
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier,
    )
}

@Composable
fun WebSocketApp(viewModel: WsViewModel = hiltViewModel()) {
    WebsocketscarletTheme {
        Surface(
            modifier = Modifier
                .statusBarsPadding()
                .navigationBarsPadding()
                .fillMaxSize()
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Button(onClick = { viewModel.startListening() }) {
                    Text("Start Listening")
                }

                val messages by viewModel.messages.collectAsState()
                Text(text = messages)

                Spacer(Modifier.weight(1f))

                Button(onClick = { viewModel.send("0.0,0.0") }) {
                    Text("0,0")
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    WebsocketscarletTheme {
        Greeting("Android")
    }
}