package com.example.p1_juegos

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.Checkbox
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.myprojects.R

data class Game(var nombre: String, var estaSeleccionado: Boolean = false, var foto: Int)

var juegos: List<Game> = listOf(
    Game("Angry Birds", foto = R.drawable.games_angrybirds),
    Game("Dragon Fly", foto = R.drawable.games_dragonfly),
    Game("Hill Climbing Racing", foto = R.drawable.games_hillclimbingracing),
    Game("Radiant Defense", foto = R.drawable.games_radiantdefense),
    Game("Pocket Soccer", foto = R.drawable.games_pocketsoccer),
    Game("Ninja Jump", foto = R.drawable.games_ninjump),
    Game("Air Control", foto = R.drawable.games_aircontrol),
    )

@Composable
fun Games() {
    val context = LocalContext.current

    Box(Modifier.padding(20.dp).fillMaxSize()) {

        Column(
           Modifier.align(Alignment.TopStart)
        ) {
            Spacer(modifier = Modifier.size(40.dp))
            CheckboxesConFoto()
        }

        FloatingActionButton(
            onClick = {
                ToastMessage(context)
            },
            Modifier
                .align(Alignment.BottomEnd)
                .padding(PaddingValues(20.dp, bottom = 40.dp)),
            containerColor = MaterialTheme.colorScheme.tertiary
        ) {
            Icon(Icons.Filled.Done, "Floating action button.")
        }
    }
}

//fun nombreImagen(nombreJuego: String): String {
//    return "games_" + nombreJuego.trim(' ').toLowerCase()
//
//}

fun ToastMessage(context: Context) {
    var juegosSeleccionados = listOf<Game>()
    var texto: String

    for (juego in juegos) {
        if (juego.estaSeleccionado) {
            juegosSeleccionados += juego
        }
    }

    if (juegosSeleccionados.isEmpty()) {
        texto = "No has seleccionado ningún juego"
    } else {
        texto = "Has seleccionado "
        for (juego in juegosSeleccionados) {
            when (juegosSeleccionados.indexOf(juego)) {
                juegosSeleccionados.size - 1 -> texto += "${juego.nombre}"
                juegosSeleccionados.size - 2 -> texto += "${juego.nombre} y "
                else -> texto += "${juego.nombre}, "
            }
        }
    }

    Toast.makeText(context, texto, Toast.LENGTH_LONG).show()
}

@Composable
fun CheckboxesConFoto() {
    for (juego in juegos) {
        var selected by remember { mutableStateOf(false) }

        Row (
            Modifier.padding(10.dp),
            verticalAlignment = Alignment.CenterVertically

        ) {
            Image(
                painter = painterResource(juego.foto),
                contentDescription = juego.nombre,
                modifier = Modifier.size(60.dp)
            )

            Checkbox(
                checked = selected,
                onCheckedChange = {
                    selected = !selected
                    juego.estaSeleccionado = selected }
            )

            Text(
                text = juego.nombre
            )
        }
    }
}