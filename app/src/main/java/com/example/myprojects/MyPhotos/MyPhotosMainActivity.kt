package com.example.di_p1_myphotoscarousel

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.myprojects.MyPhotos.LazyRowImagenes

@Composable
fun MyPhotosMainActivity() {
    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        LazyRowImagenes(Modifier.padding(innerPadding))
    }
}
