package com.alura.confereai.ui.screens.googleScan

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.sp
import com.google.mlkit.vision.barcode.common.Barcode
import com.google.mlkit.vision.codescanner.GmsBarcodeScanning

@Composable
fun GoogleScanScreen(
    modifier: Modifier = Modifier,
    onBarcodeDetected: (Barcode) -> Unit
) {
    Box(
        modifier = modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        val context = LocalContext.current
        val scanner = GmsBarcodeScanning.getClient(context)
        scanner.startScan().addOnSuccessListener { result: Barcode ->
            Log.i("resultBarcode", "result ${result.rawValue}")
            onBarcodeDetected(result)
        }
    }
}