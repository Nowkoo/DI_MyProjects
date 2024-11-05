package com.example.placesintheworld

import android.net.Uri
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
import androidx.compose.ui.platform.LocalContext
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
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.myprojects.R

data class Place (
    var nombre: String,
    @DrawableRes var foto: Int
)

data class PlaceCoil (
    var nombre: String,
    var url: String
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

            composable("PantallaInicialCoil") { PantallaInicialCoil(Modifier.padding(innerPadding), navController)}
            composable("LazyColumnVistaCoil") { LazyColumnVistaCoil(Modifier.padding(innerPadding), navController)}

            composable(
                "AboutCoil/{place}/{url}",
                arguments = listOf(
                    navArgument("place") { type = NavType.StringType },
                    navArgument("url") { type = NavType.StringType }
                )
            ) { backStackEntry ->
                AboutCoil(
                    backStackEntry.arguments?.getString("place"),
                    backStackEntry.arguments?.getString("url"),
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
                DropdownMenuItem(
                    text = { Text("LazyColumnCoil") },
                    onClick = { navController.navigate("LazyColumnVistaCoil") },
                    leadingIcon = { Icon(Icons.Filled.Face, contentDescription = null) }
                )
                DropdownMenuItem(
                    text = { Text("StaggeredGridCoil") },
                    onClick = { navController.navigate("PantallaInicialCoil") },
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

fun getPlacesCoil(): List<PlaceCoil> {
    return listOf(
        PlaceCoil("Morella", "https://media.traveler.es/photos/613762aa06df43bef2d3016a/16:9/w_2560%2Cc_limit/195207.jpg"),
        PlaceCoil("Playa Algarve", "https://www.algarveriders.com/es/docimg/1255/sd"),
        PlaceCoil("Maldivas", "https://blackpepper.travel/cache/2024-01/blackpepper-viajes-maldivas-0003-galeria-2x-1008x671.jpg"),
        PlaceCoil("Machu Pichu", "https://viajes.nationalgeographic.com.es/medio/2018/03/01/machu-picchu_5ff969ae_1280x720.jpg"),
        PlaceCoil("Gran Muralla China", "https://hips.hearstapps.com/hmg-prod/images/william-olivieri-hwy9r6-yzgm-unsplash-1646657593.jpg?crop=0.666xw:1.00xh;0.0365xw,0&resize=1120:*"),
        PlaceCoil("Alhambra", "https://www.dosde.com/discover/wp-content/uploads/2017/02/alhambra-de-granada-dosde-publishing.jpg"),
        PlaceCoil("Atenas", "https://cdn-imgix.headout.com/blog-content/image/d2cfb372cf3a129c5ee7d6d3945d0580-AdobeStock_129050920%20copy.jpg?auto=format&w=695.0400000000001&h=434.4&q=120&crop=faces&fit=crop"),
        PlaceCoil("Pirámide Kukulkan", "https://upload.wikimedia.org/wikipedia/commons/thumb/5/51/Chichen_Itza_3.jpg/1600px-Chichen_Itza_3.jpg"),
        PlaceCoil("Punta Cana", "https://upload.wikimedia.org/wikipedia/commons/thumb/6/62/Playa_Bavaro.JPG/2560px-Playa_Bavaro.JPG")
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

@Composable
fun ItemPlaceCoil(place: PlaceCoil, navController: NavController) {
    Box(
        Modifier
            .clickable {
                val encodedUrl = Uri.encode(place.url)
                navController.navigate("AboutCoil/${place.nombre}/$encodedUrl")
            }
            .padding(2.dp)
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(place.url)
                .build(),
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