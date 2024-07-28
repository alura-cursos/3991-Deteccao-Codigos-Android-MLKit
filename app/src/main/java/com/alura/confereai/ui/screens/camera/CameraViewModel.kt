package com.alura.confereai.ui.screens.camera

import androidx.lifecycle.ViewModel
import com.alura.confereai.data.Emblem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow


class CameraViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(CameraScreenUiState())
    val uiState: StateFlow<CameraScreenUiState>
        get() = _uiState.asStateFlow()

    fun setTextMessage(value: String) {
        _uiState.value = _uiState.value.copy(textMessage = value)
    }

    fun setDetectedEmblem(emblem: Emblem) {
        _uiState.value = _uiState.value.copy(detectedEmblem = emblem)
    }

}