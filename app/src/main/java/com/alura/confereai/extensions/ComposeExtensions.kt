package com.alura.confereai.extensions

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun Dp.toPx(): Float {
    val density = LocalDensity.current.density
    return value * density
}

@Composable
fun Modifier.dashedBorder(borderWidth: Dp, dashLength: Dp, gap: Dp, color: Color): Modifier {
    val value by rememberInfiniteTransition(label = "").animateFloat(
        initialValue = 0f,
        targetValue = 1000f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 2000,
                easing = FastOutSlowInEasing
            ),
            repeatMode = RepeatMode.Reverse
        ), label = ""
    )


    return this.then(
        Modifier.drawWithContent {
            drawContent()
            val pathEffect =
                PathEffect.dashPathEffect(floatArrayOf(dashLength.toPx(), gap.toPx()), value)
            val stroke = Stroke(width = borderWidth.toPx(), pathEffect = pathEffect)
            drawRoundRect(
                color = color,
                size = size,
                cornerRadius = CornerRadius(10.dp.toPx()),
                style = stroke,
            )
        }
    )
}
