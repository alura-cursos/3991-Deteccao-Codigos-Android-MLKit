package com.alura.confereai.ui.screens.camera

import android.util.Log
import androidx.annotation.OptIn
import androidx.camera.core.ExperimentalGetImage
import androidx.camera.view.CameraController
import androidx.camera.view.LifecycleCameraController
import androidx.camera.view.PreviewView
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import androidx.hilt.navigation.compose.hiltViewModel
import com.alura.confereai.R
import com.alura.confereai.data.Emblem
import com.alura.confereai.ui.components.LabelBarcode
import com.alura.confereai.utils.HandleBarcode.handleBarcode
import com.google.mlkit.vision.barcode.BarcodeScannerOptions
import com.google.mlkit.vision.barcode.BarcodeScanning
import com.google.mlkit.vision.barcode.ZoomSuggestionOptions
import com.google.mlkit.vision.barcode.common.Barcode
import com.google.mlkit.vision.common.InputImage


@OptIn(ExperimentalGetImage::class)
@Composable
fun CameraScreen() {
    val viewModel = hiltViewModel<CameraViewModel>()
    val state by viewModel.uiState.collectAsState()
    val context = LocalContext.current.applicationContext

    val lifecycleOwner = LocalLifecycleOwner.current

    var maxZoomRatio by remember {
        mutableFloatStateOf(0f)
    }

    val cameraController: LifecycleCameraController = remember {
        LifecycleCameraController(context).apply {
            setEnabledUseCases(CameraController.IMAGE_ANALYSIS)
            bindToLifecycle(lifecycleOwner)
            zoomState.observe(lifecycleOwner) {
                maxZoomRatio = it.maxZoomRatio
                Log.d("maxZoomRatio", "Zoom maximo da camera: $maxZoomRatio")
            }
        }
    }


    if (maxZoomRatio > 0f) {
        val zoomCallback = { zoomRatio: Float ->
            Log.d("zoomCallback", "Zoom do modelo $zoomRatio")
            cameraController.setZoomRatio(zoomRatio)
            true
        }

        val options = BarcodeScannerOptions.Builder()
            .enableAllPotentialBarcodes()
            .setZoomSuggestionOptions(
                ZoomSuggestionOptions.Builder(zoomCallback)
                    .setMaxSupportedZoomRatio(maxZoomRatio)
                    .build()
            ) // Optional
            .build()

        val scanner = remember {
            BarcodeScanning.getClient(options)
        }

        val cameraAnalyzer = remember {
            CameraAnalyzer { imageProxy ->

                val mediaImage = imageProxy.image
                if (mediaImage != null) {
                    val image =
                        InputImage.fromMediaImage(mediaImage, imageProxy.imageInfo.rotationDegrees)

                    scanner.process(image)
                        .addOnSuccessListener { result ->
                            if (result.isNotEmpty()) {
                                val barcode = result.first()
                                handleBarcode(barcode) { emblem: Emblem ->
                                    viewModel.setDetectedEmblem(emblem)
                                }

                                val message = result.first().rawValue
                                viewModel.setTextMessage(message.toString())
                            }
                        }
                        .addOnCompleteListener {
                            imageProxy.close()
                        }
                }


            }
        }

        cameraController.setImageAnalysisAnalyzer(
            ContextCompat.getMainExecutor(context),
            cameraAnalyzer
        )
    }

    CameraPreview(cameraController = cameraController)

    CameraOverlay(state)
}

@Composable
private fun CameraOverlay(state: CameraScreenUiState) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black.copy(alpha = 0.5f))
            .padding(top = 150.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painterResource(id = R.drawable.square_scan_shine),
            contentDescription = "Scan shine",
            modifier = Modifier
                .size(350.dp)
        )

//        state.textMessage?.let { message ->
//            Text(
//                text = message,
//                color = Color.White,
//                fontSize = 16.sp
//            )
//        }

        state.detectedEmblem?.let { emblem ->
            LabelBarcode(emblem.description)
        }
    }
}

@Composable
private fun CameraPreview(
    modifier: Modifier = Modifier,
    cameraController: LifecycleCameraController
) {
    AndroidView(
        factory = { context ->
            PreviewView(context).apply {
                this.controller = cameraController
            }
        },
        modifier = modifier.fillMaxSize()
    )
}


