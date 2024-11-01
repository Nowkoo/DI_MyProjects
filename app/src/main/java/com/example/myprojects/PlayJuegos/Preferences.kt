package com.example.p1_juegos

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun Preferences(modifier: Modifier = Modifier) {
    var sliderPosition by remember { mutableFloatStateOf(0f) }
    val radioOptions = listOf("Angry Birds", "Dragon Fly", "Hill Climbing Racing", "Pocket Soccer", "Radiant Defense", "Ninja Jump", "Air Control")
    val chipLabels = listOf("PS4", "XBOX", "3DS", "WII", "WIIU")
    var selectedOption by remember { mutableStateOf<String?>(null) }
    val context = LocalContext.current
    var selectedStar by remember { mutableIntStateOf(0) }

    Box(
        modifier.fillMaxSize()
    ) {
        Column(modifier.align(Alignment.TopCenter))  {
            Spacer(modifier = Modifier.size(60.dp))
            Text(
                text = "Elige una opción:",
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
            )
        }

        Column(
            Modifier
                .selectableGroup()
                .align(Alignment.TopStart)
                .padding(16.dp)
        ) {
            Spacer(
                modifier = Modifier.size(100.dp)
            )

            RadioButtons(
                radioOptions,
                selectedOption.toString(),
                onOptionSelected = { selectedOption = it }
            )

            SliderWithLabel(
                sliderPosition,
                onValueChange = { newValue ->
                    sliderPosition = newValue
                    selectedStar = newValue.toInt()
                }
            )

            Row(modifier = Modifier.align(Alignment.CenterHorizontally)) {
                stars(
                    selectedStar,
                    onRatingChange = { newRating ->
                        selectedStar = newRating.toInt()
                        sliderPosition = newRating
                    }
                )
            }


            Row() {
                ChipArray(chipLabels, context)
            }

        }

        FloatingActionButton(
            onClick = {
                toastMessage(selectedOption, sliderPosition, context)
            },
            modifier
                .align(Alignment.BottomEnd)
                .padding(PaddingValues(bottom = 60.dp, end = 20.dp)),
            containerColor = MaterialTheme.colorScheme.tertiary
        ) {
            Icon(Icons.Filled.Add, "Floating action button.")
        }
    }
}

@Composable
private fun stars(selectedStar: Int, onRatingChange: (Float) -> Unit) {
    for (index in 1..10) {
        var selected by remember { mutableStateOf(false) }
        IconButton(
            onClick = { selected = !selected
                onRatingChange(index.toFloat()) },
            Modifier.size(35.dp)
        ) {
            Icon(
                imageVector = Icons.Filled.Star,
                contentDescription = "star",
                tint = (
                        if (index <= selectedStar) MaterialTheme.colorScheme.tertiary
                        else Color.Gray
                        ),
            )
        }
    }
}

private fun toastMessage(
    selectedOption: String?,
    sliderPosition: Float,
    context: Context
) {
    val message = if (selectedOption == null) {
        "No has pulsado ninguna opción"
    } else {
        "Has seleccionado $selectedOption con una puntuación de ${Math.round(sliderPosition)}"
    }
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}

@Composable
private fun RadioButtons(
    radioOptions: List<String>,
    selectedOption: String,
    onOptionSelected: (String) -> Unit
) {
    radioOptions.forEach { text ->
        Row(
            Modifier
                .fillMaxWidth()
                .height(56.dp)
                .selectable(
                    selected = (text == selectedOption),
                    onClick = { onOptionSelected(text) },
                    role = Role.RadioButton
                )
                .padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            RadioButton(
                selected = (text == selectedOption),
                onClick = null
            )
            Text(
                text = text,
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(start = 16.dp)
            )
        }
    }
}

@Composable
private fun SliderWithLabel(
    sliderPosition: Float,
    onValueChange: (Float) -> Unit
) {
    Column() {
        Slider(
            value = sliderPosition,
            onValueChange = onValueChange,
            steps = 9,
            valueRange = 0f..10f
        )
    }
}


@Composable
private fun ChipArray(chipLabels: List<String>, context: Context) {
    var selectedChip: String? by remember { mutableStateOf(null) }

    for (label in chipLabels) {
        var selected by remember { mutableStateOf(false) }
        FilterChip(
            modifier = Modifier.padding(5.dp),
            onClick = {
                when {
                    selectedChip == label -> {selected = false
                        selectedChip = null}
                    selectedChip == null -> {selected = true
                        selectedChip = label
                        Toast.makeText(context, "Has seleccionado $selectedChip", Toast.LENGTH_SHORT).show()
                    }
                }
            },
            label = {
                Text(label)
            },
            selected = selected,
            leadingIcon = if (selected) {
                {
                    Icon(
                        imageVector = Icons.Filled.Done,
                        contentDescription = "Done icon",
                        modifier = Modifier.size(FilterChipDefaults.IconSize)
                    )
                }
            } else {
                null
            }
        )
    }
}