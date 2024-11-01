package com.example.p1_juegos

import android.widget.Toast
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.windowInsetsEndWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Checkbox
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.myprojects.R


data class Jugador(
    var nombre: String,
    @DrawableRes var foto: Int,
    var puntos: Int
)

@Composable
fun About() {
    val context = LocalContext.current
    LazyColumn (
        modifier = Modifier.padding(20.dp)
    ) {
        items(getJugadores()) { jugador ->
            ItemJugador(jugador = jugador) {
                Toast.makeText(context, it.nombre, Toast.LENGTH_SHORT).show()
            }
        }
    }
}

@Composable
fun ItemJugador(jugador: Jugador, onItemSelected: (Jugador) -> Unit) {
    Row (
        Modifier
            .padding(20.dp)
            .clickable { onItemSelected(jugador) }
        ,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Image(
            painter = painterResource(jugador.foto),
            contentDescription = jugador.nombre,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(100.dp)
                .clip(RoundedCornerShape(50.dp))
                .fillMaxWidth()
                .weight(1f)
        )

        Column (
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize().weight(2f)
        ) {
            Text(
                text = jugador.nombre,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "Puntos: ${jugador.puntos.toString()}"
            )
        }




    }

    HorizontalDivider()
}

fun getJugadores(): List<Jugador> {
    return listOf(
        Jugador(
            "María Mata",
            R.drawable.pj_image1,
            2014
        ),
        Jugador(
            "Antonio Sanz",
            R.drawable.pj_image2,
            2056
        ),
        Jugador(
            "Carlos Pérez",
            R.drawable.pj_image3,
            5231
        ),
        Jugador(
            "Beatriz Martos",
            R.drawable.pj_image4,
            1892
        ),
        Jugador(
            "Sandra Alegre",
            R.drawable.pj_image5,
            3400
        ),
        Jugador(
            "Alex Serrat",
            R.drawable.pj_image6,
            5874
        ),
        Jugador(
            "Ana Peris",
            R.drawable.pj_image7,
            2238
        ),
        Jugador(
            "Rodrigo Rodríguez",
            R.drawable.pj_image8,
            5673
        )
    )
}