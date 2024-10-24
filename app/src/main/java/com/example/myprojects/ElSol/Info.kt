package com.example.myprojects.ElSol

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun Info(modifier: Modifier = Modifier) {
    var currentProgress by remember { mutableStateOf(0f)}
    var loading by remember { mutableStateOf(false)}
    val scope = rememberCoroutineScope()

    var showDataPicker by remember { mutableStateOf(false)}
    var selectedDate by remember { mutableStateOf<Long?>(null)}

    Column (
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.fillMaxWidth()
    ) {
        Button(
            onClick = {
                loading = true
                scope.launch {
                    loadProgress { progress ->
                        currentProgress = progress
                    }
                    loading = false
                }
            },
            enabled = !loading
        ) {
            Text("Download more info")
        }

        if (loading) {
            LinearProgressIndicator(
                progress = {currentProgress}
            )
        }

        Button(
            onClick = { showDataPicker = true }
        ) {
            Text("Select a date to visit the Planetarium.")
        }

        if (showDataPicker) {
            createDatePicker(
                onDateSelected = {date -> selectedDate = date},
                onDismiss = {showDataPicker = false}
            )
        }
    }
}

suspend fun loadProgress(updateProgress: (Float) -> Unit) {
    for (i in 1..100) {
        updateProgress(i.toFloat() / 100)
        delay(100)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun createDatePicker(onDateSelected: (Long?) -> Unit, onDismiss:() -> Unit) {
    val datePickerState = rememberDatePickerState()

    DatePickerDialog(
        onDismissRequest = onDismiss,
        confirmButton = {
            TextButton(onClick = {
                onDateSelected(datePickerState.selectedDateMillis)
                onDismiss()
            }) {
                Text("OK")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancel")
            }
        }
    ) {
        DatePicker(state = datePickerState)
    }
}