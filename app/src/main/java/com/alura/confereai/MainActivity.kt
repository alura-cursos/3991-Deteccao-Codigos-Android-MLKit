package com.alura.confereai

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.alura.confereai.ui.screens.home.HomeNavHost
import com.alura.confereai.ui.theme.ConfereAITheme
import com.google.android.gms.common.moduleinstall.ModuleInstall
import com.google.android.gms.common.moduleinstall.ModuleInstallRequest
import com.google.mlkit.vision.codescanner.GmsBarcodeScanning
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        checkScannerAPI()

        setContent {
            ConfereAITheme {
                HomeNavHost()
            }
        }
    }

    private fun checkScannerAPI() {
        val scanner = GmsBarcodeScanning.getClient(this)
        val moduleInstallClient = ModuleInstall.getClient(this)
        val moduleInstallRequest =
            ModuleInstallRequest.newBuilder()
                .addApi(scanner)
                .build()

        moduleInstallClient
            .installModules(moduleInstallRequest)
            .addOnSuccessListener {
                if (it.areModulesAlreadyInstalled()) {
                    Log.d("StatusDownload", "Modulo já baixado")
                } else {
                    Log.d("StatusDownload", "Modulo não baixado")
                }
            }
    }
}
