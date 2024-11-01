package com.example.p1_juegos

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import com.example.myprojects.R


@Composable
fun NewPlayer(modifier: Modifier = Modifier) {
    var leftWeight = 0.5f
    var rightWeight = 1.5f

    var hayNombre by remember { mutableStateOf(true) }
    var hayApellido by remember { mutableStateOf(true) }

    var nombre by rememberSaveable() { mutableStateOf("") }
    var apellido by rememberSaveable() { mutableStateOf("") }

    Column (
//        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
            .verticalScroll(rememberScrollState()),
    ) {
        Spacer(
            modifier = Modifier.size(30.dp)
        )

        Row (
            verticalAlignment = Alignment.CenterVertically,

        ) {
            Image(
                painter = painterResource(R.drawable.account),
                contentDescription = "User",
                modifier = Modifier
                    .size(80.dp)
                    .weight(leftWeight)
            )

            TextField(
                value = nombre,
                onValueChange = {nombre = it},
                label = {Text("Nombre")},
                singleLine = true,
                modifier = Modifier.weight(rightWeight),
                shape = RoundedCornerShape(topEnd = 12.dp, topStart = 12.dp),
                colors = TextFieldDefaults.colors(focusedContainerColor = MaterialTheme.colorScheme.primary)
            )


        }

        Row (
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.height(20.dp)
        ) {
            Spacer(
                modifier = Modifier
                    .size(80.dp)
                    .weight(leftWeight)
            )

            Text(
                text = if (hayNombre) {"*Obligatorio"} else {"Error: Obligatorio"},
                modifier = Modifier.weight(rightWeight),
                color = if (hayNombre) {MaterialTheme.colorScheme.onSurface} else {MaterialTheme.colorScheme.error}
            )
        }

        Row (
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Spacer(
                modifier = Modifier
                    .size(80.dp)
                    .weight(leftWeight)
            )

            TextField(
                value = apellido,
                onValueChange = {apellido = it},
                label = {Text("Apellido")},
                singleLine = true,
                modifier = Modifier.weight(rightWeight)
            )
        }

        Row (
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.height(20.dp)
        ) {
            Spacer(
                modifier = Modifier
                    .size(80.dp)
                    .weight(leftWeight)
            )

            Text(
                text = if (hayApellido) {"*Obligatorio"} else {"Error: Obligatorio"},
                modifier = Modifier.weight(rightWeight),
                color = if (hayApellido) {MaterialTheme.colorScheme.onSurface} else {MaterialTheme.colorScheme.error}
            )
        }

        Row (
            verticalAlignment = Alignment.CenterVertically,
        ) {
            var nickname by rememberSaveable() { mutableStateOf("") }

            Spacer(
                modifier = Modifier
                    .size(80.dp)
                    .weight(leftWeight)
            )

            TextField(
                value = nickname,
                onValueChange = {nickname = it},
                label = {Text("Nickname")},
                singleLine = true,
                modifier = Modifier.weight(rightWeight)
            )
        }

        Row (
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Spacer(
                modifier = Modifier
                    .weight(1f)
                    .weight(leftWeight)
            )

            Image(
                painter = painterResource(R.drawable.android),
                contentDescription = "Android",
                modifier = Modifier
                    .size(130.dp)
                    .weight(2f)
            )

            Button(
//                contentPadding = PaddingValues(start = 6.dp),
                onClick = {},
                modifier = Modifier.weight(1.5f),
                colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.tertiary)
            ) {
                Text(
                    text = "Change"
                )
            }
        }

        Row (
            verticalAlignment = Alignment.CenterVertically,

            ) {
            Image(
                painter = painterResource(R.drawable.camera),
                contentDescription = "User",
                modifier = Modifier
                    .size(80.dp)
                    .weight(leftWeight)
            )

            var telefono by rememberSaveable() { mutableStateOf("") }

            TextField(
                value = telefono,
                onValueChange = {telefono = it},
                label = {Text("Teléfono")},
                singleLine = true,
                modifier = Modifier.weight(rightWeight)
            )
        }

        Row (
            verticalAlignment = Alignment.CenterVertically,

            ) {
            Image(
                painter = painterResource(R.drawable.email),
                contentDescription = "User",
                modifier = Modifier
                    .size(80.dp)
                    .weight(leftWeight)
            )

            var emailList = listOf("margomgar35@alu.edu.gva.es", "mgg35@alu.edu.gva.es", "mgg35@gmail.com")
            var selectedEmail by rememberSaveable() { mutableStateOf("") }
            var expanded by remember { mutableStateOf(false) }

            TextField(
                value = selectedEmail,
                onValueChange = {selectedEmail = it},
                enabled = false,
                readOnly = true,
                label = {Text("Email")},
                singleLine = true,
                colors = TextFieldDefaults.colors(
                    disabledTextColor = Color.Black
                ),
                modifier = Modifier
                    .weight(rightWeight)
                    .clickable { expanded = true }
            )

            DropdownMenu(
                expanded = expanded,
                onDismissRequest = {expanded = false},
                offset = DpOffset(x = -66.dp, y = -10.dp)

            ) {
                emailList.forEach { email ->
                    DropdownMenuItem (
                        text = { Text(text = email) },
                        onClick = {
                            expanded = false
                            selectedEmail = email
                        }
                    )
                }
            }
        }

        Row (
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Button(
                onClick = {hayNombre = nombre.isNotBlank()
                    hayApellido = apellido.isNotBlank()},
                colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.tertiary),

            ) {
                Text(
                    text = "Add new player"
                )
            }
        }
    }
}
