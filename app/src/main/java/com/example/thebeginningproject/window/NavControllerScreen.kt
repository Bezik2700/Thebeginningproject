package com.example.thebeginningproject.window

import android.view.LayoutInflater
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.DateRange
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.LocationOn
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

sealed class Navigate(val route: String){
    data object StartActivity: Navigate("StartActivity")
    data object DataActivity: Navigate("DataActivity")
}

@Composable
fun NavController(
    navController: NavHostController,
    getSpeechInput: () -> Unit,
    outputTxt: String
){
    var mapEnabled by remember { mutableStateOf(false) }
    var startEnabled by remember { mutableStateOf(true) }
    var dataEnabled by remember { mutableStateOf(false) }

    Box(modifier = Modifier){
        NavHost(
            modifier = Modifier.padding(),
            navController = navController,
            startDestination = Navigate.StartActivity.route,
        ) {
            composable(route = Navigate.StartActivity.route) {

            }
            /*composable(route = Navigate.DataActivity.route){
                DataActivity()
            }*/
        }

        /*if (mapEnabled) {
            AndroidView(factory = { context ->
                val view = LayoutInflater.from(context)
                    .inflate(R.layout.xmlview, null, false)
                view
            })
        } else if (startEnabled) {
            navController.navigate(Navigate.StartActivity.route)
        } else if (dataEnabled){
            navController.navigate(Navigate.DataActivity.route)
        }*/

        Column (
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.End,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(top = 64.dp, end = 8.dp)
        ) {
            IconButton(onClick = {
                startEnabled = !startEnabled
                mapEnabled = false
                dataEnabled = false
            }) {
                Icon(
                    Icons.Rounded.Home,
                    contentDescription = null
                )
            }
            IconButton(onClick = {
                mapEnabled = !mapEnabled
                startEnabled = false
                dataEnabled = false
            }) {
                Icon(
                    Icons.Rounded.LocationOn,
                    contentDescription = null
                )
            }
            IconButton(onClick = {
                dataEnabled = !dataEnabled
                startEnabled = false
                mapEnabled = false
            }) {
                Icon(
                    Icons.Rounded.DateRange,
                    contentDescription = null
                )
            }
        }
    }
}