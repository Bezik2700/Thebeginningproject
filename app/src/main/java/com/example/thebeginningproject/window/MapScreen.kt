package com.example.thebeginningproject.window

import android.view.LayoutInflater
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.viewinterop.AndroidView
import com.example.thebeginningproject.R

@Composable
fun MapScreen(){
    Column {
        AndroidView(factory = { context ->
            val view = LayoutInflater.from(context)
                .inflate(R.layout.mapkitlayout, null, false)
            view
        })
    }
}