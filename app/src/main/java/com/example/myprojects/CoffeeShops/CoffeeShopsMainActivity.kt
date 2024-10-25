package com.example.myprojects.CoffeeShops

import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun CoffeeShopsMainActivity() {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            CreateTopApp()
        }
    ) { innerPadding ->
        val navController = rememberNavController()
        NavHost(navController = navController, startDestination = "Shops") {
            composable("Shops") {Shops(navController, Modifier.padding(innerPadding))}
            composable(
                "ShopProfile/{shopName}",
                arguments = listOf(navArgument("shopName") { type = NavType.StringType })
            ) { backStackEntry ->
                ShopProfile(backStackEntry.arguments?.getString("shopName"), Modifier.padding(innerPadding))
            }
        }
    }
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
private fun CreateTopApp() {
    var showMenu by remember { mutableStateOf(false) }

    TopAppBar(
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            titleContentColor = MaterialTheme.colorScheme.primary
        ),
        title = {
            Text(
                "CoffeeShops",
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(PaddingValues(start = 20.dp))
            )
        },
        navigationIcon = {
            IconButton(onClick = { /* do something */ }) {
                Icon(
                    imageVector = Icons.Filled.Menu,
                    contentDescription = "Localized description"
                )
            }
        },
        actions = {

            IconButton(onClick = { showMenu = !showMenu }) {
                Icon(
                    imageVector = Icons.Filled.MoreVert,
                    contentDescription = "Localized description"
                )
            }
            DropdownMenu(
                expanded = showMenu,
                onDismissRequest = {showMenu = false}
            ) {
                DropdownMenuItem(
                    text = { Text("Compartir") },
                    onClick = {  },
                    leadingIcon = { Icon(Icons.Filled.Share, contentDescription = null) }
                )
                DropdownMenuItem(
                    text = { Text("Guardar") },
                    onClick = {  },
                    leadingIcon = { Icon(Icons.Filled.Lock, contentDescription = null) },
                )
            }
        }
    )
}