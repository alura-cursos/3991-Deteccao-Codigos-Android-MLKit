package com.alura.confereai

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.alura.confereai.ui.screens.home.HomeNavHost
import com.alura.confereai.ui.theme.ConfereAITheme
import com.google.mlkit.vision.codescanner.GmsBarcodeScanning
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val scanner = GmsBarcodeScanning.getClient(this)
        scanner.startScan()

        setContent {
            ConfereAITheme {
                HomeNavHost()
            }
        }
    }
}
