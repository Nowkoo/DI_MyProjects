package com.example.myprojects.ElSol

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.BottomAppBarDefaults
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myprojects.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun ElSolMainActivity() {
    val snackbarHostState = remember { SnackbarHostState() }

    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val navController = rememberNavController()

    ModalNavigationDrawer(
        drawerState = drawerState,
        modifier = Modifier,
        drawerContent = {
            ModalDrawerSheet {
                Image(
                    painter = painterResource(R.drawable.portada),
                    contentDescription = "Portada",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                )

                Column(
                    verticalArrangement = Arrangement.spacedBy(10.dp),
                    modifier = Modifier.padding(10.dp)
                ) {
                    NavigationDrawerItem(
                        label = { Text(text = "Build") },
                        selected = false,
                        onClick = {
                            navController.navigate("PantallaInicial")
                            scope.launch {
                                drawerState.apply {
                                    if (isClosed) open() else close()
                                }
                            } },
                        icon = {
                            Icon(
                                Icons.Filled.Build,
                                contentDescription = "Back"
                            )}
                    )
                    NavigationDrawerItem(
                        label = { Text(text = "Info") },
                        selected = false,
                        onClick = {
                            navController.navigate("Info")
                            scope.launch {
                                drawerState.apply {
                                    if (isClosed) open() else close()
                                }
                            } },
                        icon = {
                            Icon(
                                Icons.Filled.Info,
                                contentDescription = "Back"
                            )}
                    )
                    NavigationDrawerItem(
                        label = { Text(text = "Email") },
                        selected = false,
                        onClick = {},
                        icon = {
                            Icon(
                                Icons.Filled.Email,
                                contentDescription = "Back"
                            )}
                    )
                }
            }
        },
    ) {
        Scaffold(
            snackbarHost = {
                SnackbarHost(hostState = snackbarHostState)
            },
            modifier = Modifier.fillMaxSize(),
            bottomBar = { CreateBottomAppBar(drawerState, scope) }
        ) { innerPadding ->
            NavHost(navController = navController, startDestination = "PantallaInicial") {
                composable("PantallaInicial") {PantallaInicial(Modifier.padding(innerPadding), snackbarHostState)}
                composable("Info") {Info(Modifier.padding(innerPadding))}
            }
        }
    }
}

@Composable
private fun CreateBottomAppBar(drawerState: DrawerState, scope: CoroutineScope) {
    var likeCount by remember { mutableStateOf(0) }

    BottomAppBar (
        actions = {
            IconButton(onClick = {
                scope.launch {
                    drawerState.apply {
                        if (isClosed) open() else close()
                    }
                }
            }) {
                Icon(
                    Icons.Filled.ArrowBack,
                    contentDescription = "Back"
                )
            }
            BadgedBox(
                badge = {
                    if (likeCount > 0) {
                        Badge(
                            modifier = Modifier
                                .offset(x = (-15).dp, y = (7).dp)
                        ) {
                            Text(text = likeCount.toString())
                        }
                    }
                }
            ) {
                IconButton(onClick = { likeCount++ }) {
                    Icon(
                        Icons.Filled.Favorite,
                        contentDescription = "Like",
                        Modifier.size(35.dp)
                    )
                }
            }
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {},
                containerColor = BottomAppBarDefaults.bottomAppBarFabColor,
                elevation = FloatingActionButtonDefaults.bottomAppBarFabElevation()
            ) {
                Icon(Icons.Filled.Add, "Add")
            }
        }
    )
}