package com.alura.confereai.ui.screens.camera

import com.alura.confereai.data.Emblem

data class CameraScreenUiState(
    val textMessage: String? = null,
    val detectedEmblem: Emblem? = null
)
