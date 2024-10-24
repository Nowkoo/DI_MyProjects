package com.example.myprojects.MyPhotos

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.myprojects.R

data class Foto(val descripcion: String, val resourceId: Int)

@Composable
fun LazyRowImagenes(modifier: Modifier) {
    var fotoSeleccionada by remember { mutableStateOf<Int?>(null) }

    Column (horizontalAlignment = Alignment.CenterHorizontally) {
        LazyRow (
            modifier = modifier.padding(20.dp)
        ) {
            items(getFotos()) { foto ->
                ItemFoto(foto) {
                    fotoSeleccionada = foto.resourceId
                }
            }
        }

        HorizontalDivider()

        if (fotoSeleccionada != null) {
            Image(
                painter = painterResource(fotoSeleccionada!!),
                contentDescription = "",
                Modifier.size(1000.dp)
            )
        }

    }
}

@Composable
fun ItemFoto(foto: Foto, onPictureSelected: (Foto) -> Unit) {
    Image(
        painter = painterResource(foto.resourceId),
        contentDescription = foto.descripcion,
        modifier = Modifier
            .clickable { onPictureSelected(foto) }
            .padding(5.dp)
    )
}

fun getFotos(): Array<Foto> {
    return arrayOf(
        Foto ("Cola de ballena", R.drawable.image1),
        Foto ("Flores", R.drawable.image2),
        Foto ("Bosque", R.drawable.image3),
        Foto ("Desierto", R.drawable.image4),
        Foto ("Riachuelo", R.drawable.image5),
        Foto ("Colinas", R.drawable.image6),
        Foto ("Tucán", R.drawable.image7),
        Foto ("Tortuga", R.drawable.image8)
    )
}