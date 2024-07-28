package com.alura.confereai.ui.components.customshape

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.alura.confereai.R
import com.alura.confereai.data.Emblem
import com.alura.confereai.extensions.toPx
import kotlinx.coroutines.delay
import kotlin.math.cos
import kotlin.math.sin


@Composable
fun HexagonCircle(
    modifier: Modifier = Modifier,
    emblemsList: List<Emblem>,
    onScanClick: () -> Unit
) {
    val radius = 130.dp
    val hexagonSize = 130.dp
    var showAnimation by remember { mutableStateOf(false) }
    val mainSize = remember { Animatable(hexagonSize.value) }

    val rotation by animateFloatAsState(
        targetValue = if (showAnimation) 180f else 0f,
        label = "rotation",
        animationSpec = tween(
            easing = FastOutSlowInEasing,
            durationMillis = 1000
        )
    )

    LaunchedEffect(showAnimation) {
        if (showAnimation) {
            mainSize.animateTo(hexagonSize.value + 100f, tween(400))
            delay(250)
            mainSize.animateTo(hexagonSize.value, tween(400))
        }
    }

    LaunchedEffect(showAnimation) {
        if (showAnimation) {
            delay(400 + 400)
            onScanClick()
            delay(400)
            showAnimation = false
        }
    }

    Box(
        modifier = modifier
            .fillMaxSize()
            .offset(y = (-20).dp),
        contentAlignment = Alignment.Center
    ) {
        emblemsList.forEachIndexed { i, emblem ->
            val angle = Math.toRadians((i * 60).toDouble())
            val offsetX = (radius.toPx() * cos(angle)).toFloat()
            val offsetY = (radius.toPx() * sin(angle)).toFloat()

            val customModifier = Modifier
                .size(hexagonSize)
                .offset { IntOffset(offsetX.toInt(), offsetY.toInt()) }
            emblem.image?.let {
                HexagonImage(
                    customModifier,
                    image = it,
                )
            } ?: EmptyHexagon(customModifier)
        }

        Box(
            modifier = Modifier
                .graphicsLayer(
                    rotationY = rotation
                )
                .background(Color.Transparent)
        ) {
            HexagonImage(
                image = R.drawable.qr_code_image,
                backgroundColor = Color.Transparent,
                primaryBorderColor = colorResource(id = R.color.light_green),
                secondaryBorderColor = colorResource(id = R.color.light_green),
                modifier = Modifier
                    .size(mainSize.value.dp)
                    .clickable(
                        indication = null,
                        interactionSource = remember { MutableInteractionSource() },
                    ) {
                        showAnimation = true
                    }
            )
        }
    }
}