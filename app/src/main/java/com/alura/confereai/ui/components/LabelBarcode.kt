package com.alura.confereai.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.alura.confereai.R
import com.alura.confereai.extensions.dashedBorder


@Composable
fun LabelBarcode(description: String) {
    Row(
        modifier = Modifier
            .padding(vertical = 32.dp, horizontal = 16.dp)
            .background(Color.Black.copy(0.5f), shape = RoundedCornerShape(10.dp))
            .dashedBorder(
                borderWidth = 4.dp,
                dashLength = 150.dp,
                gap = 20.dp,
                color = colorResource(id = R.color.light_green)
            ),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            description,
            color = Color.White,
            modifier = Modifier.padding(16.dp),
            maxLines = 3,
            textAlign = TextAlign.Center
        )
    }
}
