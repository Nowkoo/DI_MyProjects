package com.example.placesintheworld

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderColors
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myprojects.ui.theme.saltyOcean

@Composable
fun About(nombre: String?, foto: Int?, modifier: Modifier) {
    var sliderPosition by remember { mutableFloatStateOf(0f) }

    Column (
        modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.primary),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (nombre != null) {
            Text(
                text = nombre,
                fontFamily = saltyOcean,
                fontSize = 65.sp,
                color = MaterialTheme.colorScheme.onPrimary,
                modifier =  Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )
        }

        if (foto != null) {
            Image(
                painter = painterResource(foto),
                contentDescription = nombre,
                Modifier
                    .size(300.dp)
                    .fillMaxWidth()
                    .padding(2.dp)
                    .graphicsLayer {
                        rotationY = sliderPosition
                    },
                contentScale = ContentScale.Fit
            )
        }

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(10.dp)
        ) {
            Text(
                text = "Rotación ",
                color = MaterialTheme.colorScheme.onPrimary
            )
            Slider(
                value = sliderPosition,
                onValueChange = { sliderPosition = it },
                valueRange = 0f..180f,
                colors = SliderDefaults.colors(
                    thumbColor = MaterialTheme.colorScheme.secondary,
                    activeTrackColor = MaterialTheme.colorScheme.secondary,
                    inactiveTrackColor = MaterialTheme.colorScheme.secondaryContainer
                )
            )
        }
    }
}