package com.alura.confereai.ui.screens.home

import com.alura.confereai.data.Emblem

data class HomeUiState(
    val emblemsList: List<Emblem>,
    val selectedEmblem: Emblem,
    val useGoogleScan: Boolean = false,
    val lastUpdatedIndex: Int = 0
)