package com.example.thebeginningproject.window

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.PlayArrow
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import java.util.Calendar

@Composable
fun MainScreen(
    getSpeechInput: () -> Unit,
    outputTxt: String
){

    val currentTime = Calendar.getInstance()
    val context = LocalContext.current
    /*val notificationHandler = NotificationManager(context)*/

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Card (modifier = Modifier
            .size(240.dp)
            .clip(CircleShape)
            .clickable {
                getSpeechInput()
            }
        ) {
            Column (
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxSize()
            ) {
                IconButton(onClick = {  }) {
                    Icon(
                        Icons.Rounded.PlayArrow,
                        contentDescription = null,
                        Modifier.size(160.dp)
                    )
                }
            }
        }
        Text(
            text = outputTxt,
            modifier = Modifier.padding(16.dp),
            textAlign = TextAlign.Center
        )
        Button(onClick = {
            /*notificationHandler.showSimpleNotification()*/
        }) {
            Text(text = "Simple notification")
        }
        Text(text = currentTime.get(Calendar.HOUR).toString())
        Text(text = currentTime.get(Calendar.MINUTE).toString())
    }
}