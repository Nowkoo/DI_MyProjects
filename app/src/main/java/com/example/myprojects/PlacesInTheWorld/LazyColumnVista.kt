package com.example.placesintheworld

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun LazyColumnVista(modifier: Modifier, navController: NavController) {
    LazyColumn (
        modifier.fillMaxWidth().padding(2.dp)
    ) {
        items(getPlaces()) { place ->
            ItemPlace(place, navController)
        }
    }
}

@Composable
fun LazyColumnVistaCoil(modifier: Modifier, navController: NavController) {
    LazyColumn (
        modifier.fillMaxWidth().padding(2.dp)
    ) {
        items(getPlacesCoil()) { place ->
            ItemPlaceCoil(place, navController)
        }
    }
}