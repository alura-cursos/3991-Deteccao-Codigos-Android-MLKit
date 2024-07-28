package com.alura.confereai.ui.screens.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.alura.confereai.R
import com.alura.confereai.data.Emblem
import com.alura.confereai.ui.components.customshape.HexagonCircle

@Composable
fun HomeScreen(
    switchState: Boolean = false,
    emblemsList: List<Emblem>,
    onScanClick: () -> Unit = {},
    onClickUseGoogle: (Boolean) -> Unit = {}
) {
    Box(
        modifier = Modifier
            .background(Color(0xFF18042C))
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        HexagonCircle(
            emblemsList = emblemsList,
            onScanClick = {
                onScanClick()
            }
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.event_logo),
                contentDescription = "Event Logo",
                modifier = Modifier.fillMaxWidth()
            )

            Column(
                modifier = Modifier.wrapContentSize(Alignment.Center),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    "Ande pelo evento e sempre que vir um QR Code, escaneie-o para liberar conteúdos exclusivos!",
                    color = Color.White,
                    fontSize = 18.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(vertical = 32.dp),
                )

                Row(
                    modifier = Modifier,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text(
                            "Use Google Play Services",
                            color = Color.White,
                            fontSize = 16.sp,
                            textAlign = TextAlign.Center,
                        )
                        Text(
                            "Não requer permissão de câmera",
                            color = Color.White.copy(alpha = 0.5f),
                            fontSize = 16.sp,
                            textAlign = TextAlign.Center,
                        )
                    }
                    Spacer(modifier = Modifier.size(32.dp))
                    Switch(
                        checked = switchState,
                        onCheckedChange = {
                            onClickUseGoogle(!switchState)
                        },
                        colors = SwitchDefaults.colors(
                            checkedThumbColor = colorResource(id = R.color.dark_green),
                            checkedTrackColor = colorResource(id = R.color.light_green),
                            checkedBorderColor = colorResource(id = R.color.dark_green),
                            uncheckedThumbColor = colorResource(id = R.color.dark_purple),
                            uncheckedTrackColor = colorResource(id = R.color.light_purple),
                            uncheckedBorderColor = colorResource(id = R.color.dark_purple)
                        )
                    )
                }
                Spacer(modifier = Modifier.size(16.dp))
            }
        }
    }
}


@Composable
@Preview
fun HomeScreenPreview() {
    val emblems = MutableList(5) { Emblem() }
    emblems.add(Emblem(image = R.drawable.default_image))
    HomeScreen(
        emblemsList = emblems
    )
}