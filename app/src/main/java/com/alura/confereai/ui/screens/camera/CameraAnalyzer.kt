package com.alura.confereai.ui.screens.camera

import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageProxy

class CameraAnalyzer(
    private val onImageProxy: (image: ImageProxy) -> Unit
) : ImageAnalysis.Analyzer {
    override fun analyze(image: ImageProxy) {
        onImageProxy(image)
    }
}
