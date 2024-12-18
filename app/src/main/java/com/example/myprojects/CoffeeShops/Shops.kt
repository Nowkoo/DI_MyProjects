package com.example.myprojects.CoffeeShops

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.myprojects.R
import com.example.myprojects.ui.theme.titulo

data class Shop (
    var nombre: String,
    var direccion: String,
    var valoraciones: String,
    var selectedStar: Int,
    @DrawableRes var foto: Int
)

@Composable
fun Shops(navController: NavHostController, modifier: Modifier) {
    LazyColumn(modifier.fillMaxWidth()) {
        items(getShops()) { shop ->
            ItemShop(shop, navController)
        }
    }
}

@Composable
fun ItemShop(shop: Shop, navController: NavHostController) {
    var selectedStar by remember { mutableIntStateOf(0) }
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp)
            .shadow(10.dp)
            .clickable { navController.navigate("ShopProfile/${shop.nombre}") }
    ) {
        Image(
            painter = painterResource(shop.foto),
            contentDescription = shop.nombre,
            Modifier
                .fillMaxWidth()
                .size(200.dp)
                .padding(bottom = 20.dp),
            contentScale = ContentScale.Crop
        )

        Column (
            Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            Text(
                text = shop.nombre,
                fontFamily = titulo,
                fontSize = 30.sp,
            )

            Row() {
                stars(
                    selectedStar,
                    onRatingChange = { newRating ->
                        selectedStar = newRating.toInt()
                        shop.selectedStar = selectedStar
                    }
                )
            }

            Text(
                text = shop.direccion,
            )

            HorizontalDivider()


            Button(
                onClick = {},
//                modifier = Modifier.align(Alignment.CenterHorizontally)
            ) {
                Text(
                    text = "BOOK"
                )
            }
        }
    }
}

fun getShops(): List<Shop> {
    return listOf(
        Shop(
            nombre = "Antico Caffè Greco", "St. Italy, Rome", "", 0, R.drawable.coffeeshops
        ),
        Shop(
            nombre = "Coffee Room", "St. Germany, Berlin", "", 0, R.drawable.coffeeshops1
        ),
        Shop(
            nombre = "Coffee Ibiza", "St. Colon, Madrid", "", 0, R.drawable.coffeeshops2
        ),
        Shop(
            nombre = "Pudding Coffee Shop", "St. Diagonal, Barcelona", "", 0, R.drawable.coffeeshops3
        ),
        Shop(
            nombre = "L'Express", "St. Picadilly Circus, London", "", 0, R.drawable.coffeeshops4
        ),
        Shop(
            nombre = "Coffee Corner", "St. Àngel Guimerà, Valencia", "", 0, R.drawable.coffeeshops5
        ),
        Shop(
            nombre = "Sweet Cup", "St. Kinkerstraat, Amsterdam", "", 0, R.drawable.coffeeshops6
        )
    )
}

@Composable
private fun stars(selectedStar: Int, onRatingChange: (Float) -> Unit) {
    for (index in 1..5) {
        var selected by remember { mutableStateOf(false) }
        IconButton(
            onClick = { selected = !selected
                onRatingChange(index.toFloat()) },
            Modifier.size(35.dp)
        ) {
            Icon(
                imageVector = Icons.Filled.Star,
                contentDescription = "star",
                tint = (
                        if (index <= selectedStar) MaterialTheme.colorScheme.tertiary
                        else Color.Gray
                        ),
            )
        }
    }
}