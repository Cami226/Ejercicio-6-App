package com.bootcamp.ejercicio6app.components



import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.bootcamp.ejercicio6app.model.Movie
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.bootcamp.ejercicio6app.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppButt(
    title: String,
    onBackNavClicked: () -> Unit = {}
) {
    val navigationIcon: (@Composable () -> Unit)? =
        if (!title.contains(stringResource(R.string.app_name))) {
            {
                IconButton(
                    onClick = { onBackNavClicked() }
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        tint = Color.White,
                        contentDescription = null
                    )
                }
            }
        } else {
            null
        }

    TopAppBar(
        title = {
            Text(
                text = title,
                color = colorResource(R.color.white),
                modifier = Modifier
                    .padding(start = 4.dp)
                    .heightIn(max = 24.dp)
            )
        },
        navigationIcon = navigationIcon
            ?: {},
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color(0xFF6200EE)
        )
    )
}


@Composable
fun AppBodd(
    onDismiss: () -> Unit,
    onAddMovie: (Movie) -> Unit
) {
    var title by remember { mutableStateOf("") }
    var overview by remember { mutableStateOf("") }
    var releaseDate by remember { mutableStateOf("") }
    var voteAverage by remember { mutableStateOf("") }

    AlertDialog(
        onDismissRequest = { onDismiss() },
        title = { Text(text = "Agregar Película") },
        text = {
            Column(modifier = Modifier.fillMaxWidth(), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                OutlinedTextField(
                    value = title,
                    onValueChange = { title = it },
                    label = { Text(text = "Título") },
                    modifier = Modifier.fillMaxWidth()
                )
                OutlinedTextField(
                    value = overview,
                    onValueChange = { overview = it },
                    label = { Text(text = "Descripción") },
                    modifier = Modifier.fillMaxWidth()
                )
                OutlinedTextField(
                    value = releaseDate,
                    onValueChange = { releaseDate = it },
                    label = { Text(text = "Fecha de Lanzamiento") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    modifier = Modifier.fillMaxWidth()
                )
                OutlinedTextField(
                    value = voteAverage,
                    onValueChange = { voteAverage = it },
                    label = { Text(text = "Calificación") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    modifier = Modifier.fillMaxWidth()
                )
            }
        },
        confirmButton = {
            TextButton(onClick = {
                val newMovie = Movie(
                    id = 0,
                    title = title,
                    overview = overview,
                    release_date = releaseDate,
                    vote_average = voteAverage.toDoubleOrNull() ?: 0.0,
                    poster_path = ""
                )
                onAddMovie(newMovie)
                onDismiss()
            }) {
                Text(text = "Agregar")
            }
        },
        dismissButton = {
            TextButton(onClick = { onDismiss() }) {
                Text(text = "Cancelar")
            }
        }
    )
}