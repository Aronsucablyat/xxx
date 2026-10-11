package com.brahma.bioquaderno

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.brahma.bioquaderno.platform.Platform
import com.brahma.bioquaderno.ui.AppRoot

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        Platform.init(applicationContext)
        setContent {
            AppRoot()
        }
    }
}