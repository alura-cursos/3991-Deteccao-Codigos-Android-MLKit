package com.alura.confereai.ui.screens.home

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.alura.confereai.ui.screens.camera.AnalyzeScreen
import com.alura.confereai.ui.screens.detail.DetailCodeScreen
import com.alura.confereai.ui.screens.googleScan.GoogleScanScreen
import kotlinx.coroutines.launch

internal const val homeRoute = "home"
internal const val cameraRoute = "camera"
internal const val googleScanRoute = "googleCamera"
internal const val detailRoute = "detail"


@Composable
fun HomeNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
    val homeViewModel = hiltViewModel<HomeViewModel>()
    val state by homeViewModel.uiState.collectAsState()


    Scaffold(
        modifier = Modifier,
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .background(Color(0xFF18042C))
                .fillMaxSize()
                .padding(paddingValues),
            contentAlignment = Alignment.Center
        ) {

            val scope = rememberCoroutineScope()

            NavHost(
                navController = navController,
                startDestination = homeRoute,
                modifier = modifier,
                enterTransition = { fadeIn(tween(200)) },
                exitTransition = { fadeOut(tween(200)) },
            ) {
                composable(homeRoute) {
                    HomeScreen(
                        switchState = state.useGoogleScan,
                        emblemsList = state.emblemsList,
                        onScanClick = {
                            if (state.useGoogleScan) {
                                navController.navigate(googleScanRoute)
                            } else {
                                navController.navigate(cameraRoute)
                            }
                        },
                        onClickUseGoogle = {
                            homeViewModel.setGoogleScan(it)
                        }
                    )
                }

                composable(cameraRoute) {
                    AnalyzeScreen()
                }

                composable(googleScanRoute) {
                    GoogleScanScreen(
                        onBarcodeDetected = {
                            scope.launch {
                                navController.navigateUp()
                                navController.navigate(detailRoute)
                            }
                        },
                    )

                }

                composable(detailRoute) {
                    DetailCodeScreen(
                        emblem = state.selectedEmblem,
                        onClicked = {}
                    )
                }
            }
        }
    }
}

