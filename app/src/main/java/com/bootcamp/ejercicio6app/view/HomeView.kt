package com.bootcamp.ejercicio6app.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.bootcamp.ejercicio6app.model.Movies
import com.bootcamp.ejercicio6app.model.Movie
import com.bootcamp.ejercicio6app.viewModel.MovieViewModel
import coil.compose.AsyncImage
@Composable
fun HomeView(viewModel: MovieViewModel ) {
    val movie by viewModel.movies.collectAsState(initial = emptyList())
    LazyColumn {
        items(movie) { movie ->
            MovieCard(movie)
        }
    }
}
@Composable
fun MovieCard(movies: Movie) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = "Title: ${movies.title}")
            Text(text = "id: ${movies.id}")
            Text(text = "Rating: ${movies.vote_average}")
            Text(text = "Overview: ${movies.overview}")
            AsyncImage(
                model = "https://image.tmdb.org/t/p/w500${movies.poster_path}",
                contentDescription = movies.title,
                modifier = Modifier
                    .height(200.dp)
                    .fillMaxWidth(),
                contentScale = ContentScale.Crop
            )
            Text(text = "Release Date: ${movies.release_date}")
            Text(text = "vote average: ${movies.vote_average}")
        }
    }
}