package com.example.p1_juegos

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.myprojects.ui.theme.FontTittle
import com.example.myprojects.ui.theme.Pink40

@Composable
fun Portada(navController: NavHostController) {
    val configuracion = LocalConfiguration.current

    when (configuracion.orientation) {
        Configuration.ORIENTATION_LANDSCAPE -> {
            Column (
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxSize()
            ) {
                Text(
                    text = "Play Juegos",
                    modifier = Modifier,
                    fontSize = 40.sp,
                    fontStyle = FontStyle.Italic,
                    fontFamily = FontTittle
                )

                Spacer(
                    modifier = Modifier.size(20.dp)
                )

                Row {
                    Column {
                        Button(
                            onClick = {navController.navigate("Games")},
                            modifier = Modifier
                                .width(180.dp),
                            colors = ButtonDefaults.buttonColors(Pink40)
                        ) {
                            Text(
                                text = "Play",
                                fontSize = 15.sp
                            )
                        }

                        Button(
                            onClick = {navController.navigate("NewPlayer")},
                            modifier = Modifier
                                .width(180.dp)
                        ) {
                            Text(
                                text = "New Player",
                                fontSize = 15.sp
                            )
                        }
                    }

                    Spacer(
                        modifier = Modifier.size(50.dp)
                    )

                    Column {
                        Button(
                            onClick = {navController.navigate("Preferences")},
                            modifier = Modifier
                                .width(180.dp)
                        ) {
                            Text(
                                text = "Preferences",
                                fontSize = 15.sp
                            )
                        }

                        Button(
                            onClick = {navController.navigate("About")},
                            modifier = Modifier
                                .width(180.dp)
                        ) {
                            Text(
                                text = "About",
                                fontSize = 15.sp
                            )
                        }
                    }
                }
            }
        } else -> {
        Column (
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize(),
        ) {
            Text(
                text = "Play Juegos",
                fontSize = 40.sp,
                fontStyle = FontStyle.Italic,
                fontFamily = FontTittle
            )

            Spacer(
                modifier = Modifier.size(20.dp)
            )

            Button(
                onClick = {navController.navigate("Games")},
                modifier = Modifier
                    .width(180.dp),
                colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.tertiary)
            ) {
                Text(
                    text = "Play",
                    fontSize = 15.sp
                )
            }

            Button(
                onClick = {navController.navigate("NewPlayer")},
                modifier = Modifier
                    .width(180.dp)
            ) {
                Text(
                    text = "New Player",
                    fontSize = 15.sp
                )
            }

            Button(
                onClick = {navController.navigate("Preferences")},
                modifier = Modifier
                    .width(180.dp)
            ) {
                Text(
                    text = "Preferences",
                    fontSize = 15.sp
                )
            }

            Button(
                onClick = {navController.navigate("About")},
                modifier = Modifier
                    .width(180.dp)
            ) {
                Text(
                    text = "About",
                    fontSize = 15.sp
                )
            }
        }
    }
    }

}