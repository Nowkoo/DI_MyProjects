package com.example.myprojects

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.AccountBox
import androidx.compose.material.icons.outlined.Face
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.di_p1_myphotoscarousel.MyPhotosMainActivity
import com.example.myprojects.ElSol.ElSolMainActivity
import com.example.myprojects.ui.theme.MyProjectsTheme

val items = listOf("MyPhotos", "CoffeeShops", "ElSol")
val selectedIcons = listOf(Icons.Filled.AccountBox, Icons.Filled.Favorite, Icons.Filled.Face)
val unselectedIcons = listOf(Icons.Outlined.AccountBox, Icons.Outlined.Favorite, Icons.Outlined.Face)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyProjectsTheme {
                val navController = rememberNavController()
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    bottomBar = { CreateNavigationBar(navController) }
                ) { innerPadding ->
                    NavHost(navController = navController, startDestination = "MyProjectsInicio") {
                        composable("MyProjectsInicio") { MyProjectsInicio(Modifier.padding(innerPadding))}
                        composable("MyPhotosMainActivity") { MyPhotosMainActivity()}
                        composable("ElSolMainActivity") { ElSolMainActivity()}
                    }
                }
            }
        }
    }
}

@Composable
fun CreateNavigationBar(navController: NavHostController) {
    var selectedItem by remember { mutableStateOf(0)}
    NavigationBar {
        items.forEachIndexed { index, item ->
            NavigationBarItem(
                icon = {
                    Icon(
                        if (selectedItem == index) {
                            selectedIcons[index]
                        } else {
                            unselectedIcons[index]
                        },
                        contentDescription = item
                    )
                },
                label = { Text(item) },
                selected = selectedItem == index,
                onClick = { selectedItem = index
                when (selectedItem) {
                    0 -> {navController.navigate("MyPhotosMainActivity")}
                    1 -> {navController.navigate("CoffeeShopsMainActivity")}
                    2 -> {navController.navigate("ElSolMainActivity")}
                }
                }
            )
        }
    }
}