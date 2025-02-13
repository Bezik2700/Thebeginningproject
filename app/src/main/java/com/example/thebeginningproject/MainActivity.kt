package com.example.thebeginningproject

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.speech.RecognizerIntent
import android.speech.SpeechRecognizer
import android.widget.Toast.LENGTH_SHORT
import android.widget.Toast.makeText
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.compose.rememberNavController
import com.example.thebeginningproject.setting.DataStoreManager
import com.example.thebeginningproject.ui.theme.TheBeginningProjectTheme
import com.example.thebeginningproject.window.NavController
import com.yandex.mapkit.MapKitFactory
import java.util.Locale

class MainActivity : ComponentActivity() {

    private var outputTxt by mutableStateOf("")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // data store manager
        val dataStoreManager = DataStoreManager(this)
        // yandex mapkit activity
        MapKitFactory.setApiKey("cf962e54-b043-4f00-b2db-d301ee991e32")
        MapKitFactory.initialize(this)

        setContent {
            TheBeginningProjectTheme {

                val counter = remember { mutableIntStateOf(1) }

                LaunchedEffect(key1 = true) {
                    dataStoreManager.getSettings().collect { settings ->
                        counter.intValue = settings.counter
                    }
                }

                NavController(
                    navController = rememberNavController(),
                    outputTxt = outputTxt,
                    getSpeechInput = {getSpeechInput(this)},
                    dataStoreManager = dataStoreManager,
                    counter = counter
                )
            }
        }
    }
    private fun getSpeechInput(context: Context) {
        if (!SpeechRecognizer.isRecognitionAvailable(context)) {
            makeText(context, "Speech not Available", LENGTH_SHORT).show()
        } else {
            val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
            intent.putExtra(
                RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_WEB_SEARCH
            )
            intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault())
            intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Speak Something")
            startActivityForResult(intent, 101)
        }
    }

    @Deprecated("")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 101 && resultCode == Activity.RESULT_OK) {
            val result = data?.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)
            outputTxt = result?.get(0).toString()
        }
    }
}