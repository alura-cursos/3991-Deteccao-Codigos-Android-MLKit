package com.alura.confereai.ui.components.customshape

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.alura.confereai.R


@Composable
fun HexagonImage(
    modifier: Modifier = Modifier,
    image: Int = R.drawable.default_image,
    description: String = "Image",
    size: Dp = 200.dp,
    backgroundColor: Color = colorResource(id = R.color.light_purple),
    primaryBorderColor: Color = Color(0xFFFFEB3B),
    secondaryBorderColor: Color = Color(0xFFF60000),
    borderWidth: Dp = 8.dp
) {
    val shapeHexagon = HexagonShape()
    val gradient = listOf(secondaryBorderColor, primaryBorderColor)
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .drawWithContent {
                drawContent()
                drawPath(
                    path = drawCustomHexagonPath(this.size),
                    style = Stroke(
                        width = borderWidth.toPx(),
                        pathEffect = PathEffect.cornerPathEffect(40f)
                    ),
                    brush = Brush.linearGradient(
                        colors = gradient,
                    )
                )
            }
            .background(backgroundColor, shape = shapeHexagon)
            .wrapContentSize(),
    ) {
        Image(
            painter = painterResource(image),
            contentDescription = description,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .graphicsLayer {
                    this.shape = shapeHexagon
                    this.clip = true
                }
                .size(size)
                .clip(shapeHexagon)
        )
    }
}


@Composable
fun EmptyHexagon(
    modifier: Modifier = Modifier,
    size: Dp = 200.dp,
    backgroundColor: Color = colorResource(id = R.color.light_purple),
    borderColor: Color = colorResource(id = R.color.dark_purple),
    borderWidth: Dp = 8.dp
) {
    val shapeHexagon = HexagonShape()
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .drawWithContent {
                drawContent()
                drawPath(
                    path = drawCustomHexagonPath(this.size),
                    color = borderColor,
                    style = Stroke(
                        width = borderWidth.toPx(),
                        pathEffect = PathEffect.cornerPathEffect(40f)
                    )
                )
            }
            .size(size)
            .background(backgroundColor, shape = shapeHexagon)
            .wrapContentSize(),
    ) {

        Text(
            text = "?",
            color = Color(0xFFFFFFFF),
            fontSize = 72.sp,
            fontWeight = FontWeight.Bold
        )

    }
}

