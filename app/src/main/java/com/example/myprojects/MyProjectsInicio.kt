package com.example.myprojects

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun MyProjectsInicio(modifier: Modifier) {
    Box(
        modifier = modifier.fillMaxSize()
    ) {
        Column(modifier.align(Alignment.Center))  {
            Text(
                text = "MyProjects",
                fontWeight = FontWeight.Bold,
                fontSize = 40.sp
            )
        }
    }
}