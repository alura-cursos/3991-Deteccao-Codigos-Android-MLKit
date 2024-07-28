package com.alura.confereai.ui.screens.googleScan

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp

@Composable
fun GoogleScanScreen(
    modifier: Modifier = Modifier,
    onBarcodeDetected: () -> Unit
) {
    Box(
        modifier = modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "Google Scan",
            fontSize = 24.sp,
            color = Color.White,
            modifier = Modifier.background(Color.Black)
        )
    }
}