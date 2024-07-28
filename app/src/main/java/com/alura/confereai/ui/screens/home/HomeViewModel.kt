package com.alura.confereai.ui.screens.home

import androidx.lifecycle.ViewModel
import com.alura.confereai.data.Emblem
import com.alura.confereai.utils.ActionHandler
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val actionHandler: ActionHandler
) : ViewModel() {
    private val _uiState = MutableStateFlow(
        HomeUiState(
            emblemsList = emptyList(),
            selectedEmblem = Emblem()
        )
    )
    var uiState = _uiState.asStateFlow()


    init {
        loadEmblems()
    }

    private fun loadEmblems() {
        val emblems = MutableList(6) { Emblem() }
        _uiState.value = _uiState.value.copy(
            emblemsList = emblems
        )
    }

    fun setGoogleScan(value: Boolean) {
        _uiState.value = _uiState.value.copy(
            useGoogleScan = value
        )
    }

    private fun setSelectEmblem(emblemResult: Emblem) {
        with(_uiState.value) {
            if (!emblemsList.contains(emblemResult)) {
                val updatedList = emblemsList.map { emblem ->
                    if (emblemsList.indexOf(emblem) == lastUpdatedIndex) {
                        emblemResult
                    } else {
                        emblem
                    }
                }

                _uiState.value = _uiState.value.copy(
                    emblemsList = updatedList,
                    selectedEmblem = emblemResult,
                    lastUpdatedIndex = lastUpdatedIndex + 1
                )
            }
        }
    }
}