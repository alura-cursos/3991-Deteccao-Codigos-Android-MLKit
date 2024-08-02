package com.alura.confereai.ui.screens.home

import com.alura.confereai.data.Emblem

data class HomeUiState(
    val emblemsList: List<Emblem>,
    val selectedEmblem: Emblem,
    val useGoogleScan: Boolean = true,
    val lastUpdatedIndex: Int = 0
)