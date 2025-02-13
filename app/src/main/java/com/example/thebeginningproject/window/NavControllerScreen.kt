package com.example.thebeginningproject.window

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
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.thebeginningproject.setting.DataStoreManager

sealed class Navigate(val route: String){
    data object MapScreen: Navigate("MapScreen")
    data object MainScreen: Navigate("MainScreen")
    data object DataScreen: Navigate("DataScreen")
}

@Composable
fun NavController(
    dataStoreManager: DataStoreManager,
    counter: MutableState<Int>,
    navController: NavHostController,
    getSpeechInput: () -> Unit,
    outputTxt: String
){
    Box(modifier = Modifier){
        NavHost(
            modifier = Modifier.padding(),
            navController = navController,
            startDestination = Navigate.MainScreen.route,
        ) {
            composable(route = Navigate.MainScreen.route) {
                MainScreen(
                    outputTxt = outputTxt,
                    getSpeechInput = getSpeechInput
                )
            }
            composable(route = Navigate.MapScreen.route) {
                MapScreen()
            }
            composable(route = Navigate.DataScreen.route) {
                DataScreen()
            }
        }

        Column (
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.End,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(top = 64.dp, end = 8.dp)
        ) {
            IconButton(onClick = {navController.navigate(Navigate.MainScreen.route)}) {
                Icon(
                    Icons.Rounded.Home,
                    contentDescription = null
                )
            }
            IconButton(onClick = {navController.navigate(Navigate.MapScreen.route)}) {
                Icon(
                    Icons.Rounded.LocationOn,
                    contentDescription = null
                )
            }
            IconButton(onClick = {navController.navigate(Navigate.DataScreen.route)}) {
                Icon(
                    Icons.Rounded.DateRange,
                    contentDescription = null
                )
            }
        }
    }
}