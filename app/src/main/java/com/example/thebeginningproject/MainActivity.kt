package com.example.thebeginningproject

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.thebeginningproject.ui.theme.TheBeginningProjectTheme
import com.example.thebeginningproject.window.MapScreen
import com.yandex.mapkit.MapKitFactory

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // yandex mapkit activity
        MapKitFactory.setApiKey("cf962e54-b043-4f00-b2db-d301ee991e32")
        MapKitFactory.initialize(this)

        setContent {
            TheBeginningProjectTheme {
                MapScreen()
            }
        }
    }
}