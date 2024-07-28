package com.alura.confereai.ui.screens.detail

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.alura.confereai.R
import com.alura.confereai.data.Emblem
import com.alura.confereai.ui.components.customshape.HexagonImage
import kotlinx.coroutines.delay

@Composable
fun DetailCodeScreen(
    emblem: Emblem,
    modifier: Modifier = Modifier,
    onClicked: (Emblem) -> Unit = {}
) {
    val rotation = remember { Animatable(180f) }
    LaunchedEffect(true) {
        delay(100)
        rotation.animateTo(
            360f,
            animationSpec = tween(
                500,
                easing = FastOutSlowInEasing,
            ),
        )
    }

    Column(
        modifier = modifier
            .background(Color(0xFF18042C))
            .fillMaxSize()
            .padding(horizontal = 16.dp, vertical = 72.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val hexagonSize = 250.dp
        Column(
            modifier = modifier
                .background(Color(0xFF18042C))
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            HexagonImage(
                modifier = Modifier
                    .size(hexagonSize)
                    .graphicsLayer(rotationY = rotation.value),
                image = emblem.image ?: R.drawable.default_image,
                size = hexagonSize,
                borderWidth = 8.dp,
                backgroundColor = Color.Transparent
            )
            Spacer(modifier = Modifier.height(22.dp))
            Text(
                text = emblem.title,
                style = MaterialTheme.typography.bodySmall,
                color = Color.Yellow,
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = emblem.description,
                color = Color.White,
                fontSize = 18.sp,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(48.dp))
        }

        Button(
            onClick = {
                onClicked(emblem)
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp, horizontal = 8.dp)
                .sizeIn(minHeight = 72.dp),
            shape = RoundedCornerShape(20.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = colorResource(id = R.color.light_green),
                contentColor = Color(0xFF201539)
            )
        ) {
            Text(
                text = emblem.action,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}