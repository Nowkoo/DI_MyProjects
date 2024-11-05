package com.example.placesintheworld

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.myprojects.R

data class Place (
    var nombre: String,
    @DrawableRes var foto: Int
)

@Composable
fun PlacesInTheWorldMainActivity() {
    val navController = rememberNavController()
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = { CreateTopAppBar(navController) }
    ) { innerPadding ->
        NavHost(navController = navController, startDestination = "PantallaInicial") {
            composable("PantallaInicial") { PantallaInicial(Modifier.padding(innerPadding), navController)}
            composable("LazyColumnVista") { LazyColumnVista(Modifier.padding(innerPadding), navController)}
            composable(
                "About/{place}/{foto}",
                arguments = listOf(
                    navArgument("place") { type = NavType.StringType },
                    navArgument("foto") { type = NavType.IntType }
                )
            ) { backStackEntry ->
                About(
                    backStackEntry.arguments?.getString("place"),
                    backStackEntry.arguments?.getInt("foto"),
                    Modifier.padding(innerPadding)
                )
            }
        }

        Box(Modifier.padding(20.dp).fillMaxSize()) {
            FloatingActionButton(
                onClick = { navController.navigate("pantallaInicial") },
                Modifier
                    .align(Alignment.BottomEnd)
                    .padding(bottom = 40.dp),
                containerColor = MaterialTheme.colorScheme.secondary
            ) {
                Icon(Icons.Filled.ArrowBack, "Floating action button.")
            }
        }

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateTopAppBar(navController: NavController) {
    var showMenu by remember { mutableStateOf(false)}

    TopAppBar(
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary,
            titleContentColor = MaterialTheme.colorScheme.onPrimary,
            actionIconContentColor = MaterialTheme.colorScheme.onPrimary,
            navigationIconContentColor = MaterialTheme.colorScheme.onPrimary
        ),
        title = {
            Text(
                text = "PlacesInTheWorld",
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
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
                    text = { Text("LazyColumn") },
                    onClick = { navController.navigate("LazyColumnVista") },
                    leadingIcon = { Icon(Icons.Filled.Face, contentDescription = null) }
                )
                DropdownMenuItem(
                    text = { Text("StaggeredGrid") },
                    onClick = { navController.navigate("PantallaInicial") },
                    leadingIcon = { Icon(Icons.Filled.Home, contentDescription = null) },
                )
            }
        }
    )
}

fun getPlaces(): List<Place> {
    return listOf(
        Place("Morella", R.drawable.places_image),
        Place("Playa Algarve", R.drawable.places_image1),
        Place("Maldivas", R.drawable.places_image2),
        Place("Machu Pichu", R.drawable.places_image3),
        Place("Gran Muralla China", R.drawable.places_image4),
        Place("Alhambra", R.drawable.places_image5),
        Place("Atenas", R.drawable.places_image6),
        Place("Pirámide Kukulkan", R.drawable.places_image7),
        Place("Punta Cana", R.drawable.places_image8)
    )
}

@Composable
fun ItemPlace(place: Place, navController: NavController) {
    Box(
        Modifier
            .clickable { navController.navigate("About/${place.nombre}/${place.foto}") }
            .padding(2.dp)
    ) {
        Image(
            painter = painterResource(place.foto),
            contentDescription = place.nombre,
            Modifier
                .fillMaxWidth(),
            contentScale = ContentScale.Crop
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Black.copy(alpha = 0.3f))
                .align(Alignment.TopStart)
                .padding(8.dp)
        ) {
            Text(
                text = place.nombre,
                color = Color.White,
                fontSize = 22.sp,
                textDecoration = TextDecoration.Underline,
                modifier = Modifier.padding(5.dp)
            )
        }
    }
}