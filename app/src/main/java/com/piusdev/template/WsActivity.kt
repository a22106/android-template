package com.piusdev.template

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.piusdev.feature.temp.WsViewModel
import com.piusdev.template.ui.theme.WebsocketscarletTheme
import dagger.hilt.android.AndroidEntryPoint
import com.piusdev.feature.temp.WebSocketTest

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
fun WebSocketApp(viewModel: WsViewModel = hiltViewModel()) {
//    val scaffoldState = rememberScaffoldState()

    WebsocketscarletTheme {
        Surface(
            modifier = Modifier
                .statusBarsPadding()
                .navigationBarsPadding()
                .fillMaxSize()
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                WebSocketTest(viewModel)
//                ApiGetTest(viewModel)
            }
        }
    }
}


