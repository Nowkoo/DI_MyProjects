package com.example.p1_juegos

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun PlayJuegosMainActivity() {
    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        val navController = rememberNavController()
        NavHost(navController = navController, startDestination = "SplashScreen") {
            composable("SplashScreen") { SplashScreen(navController) }
            composable("Portada") {Portada(navController)}
            composable("NewPlayer") {NewPlayer(Modifier.padding(innerPadding))}
            composable("Preferences") { Preferences()}
            composable("Games") { Games() }
            composable("About") { About() }
        }

//                    NewPlayer(
//                        modifier = Modifier.padding(innerPadding)
//                    )
    }
}