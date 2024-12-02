package com.bootcamp.ejercicio6app.utils

import com.bootcamp.ejercicio6app.di.RepositoryModule
import com.bootcamp.ejercicio6app.model.Movie
import com.bootcamp.ejercicio6app.model.MovieResponse
import com.bootcamp.ejercicio6app.model.Movies
import com.bootcamp.ejercicio6app.repository.MovieRepository
import dagger.hilt.testing.TestInstallIn
import dagger.Module
import dagger.Provides
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Singleton
@Module
@TestInstallIn(
    components = [SingletonComponent::class],

    replaces = [RepositoryModule::class]
)
class FakeRepositoryModule {
    @Provides
    @Singleton
    fun movieRepository():MovieRepository = object : MovieRepository {
        private val movieList = mutableListOf<Movie>()
        private val movieFlow = MutableStateFlow<List<Movie>>(movieList)


        override suspend fun getPopularMoviesApi(): MovieResponse {
            val moviesFromApi = listOf(
                Movies(
                    id = 1,
                    title = "The Odyssey",
                    overview = "An epic adventure.",
                    poster_path = "/path/to/image.jpg",
                    release_date = "2023-05-03",
                    vote_average = 8.2
                ),
                Movies(
                    id = 2,
                    title = "Journey to the Unknown",
                    overview = "A mysterious adventure.",
                    poster_path = "/path/to/image2.jpg",
                    release_date = "2022-10-15",
                    vote_average = 7.5
                )
            )
            // Convertimos los datos a `Movie` y los insertamos en la lista local
            moviesFromApi.forEach { movie ->
                val movieToRoom = Movie(
                    id = movie.id,
                    title = movie.title,
                    overview = movie.overview,
                    poster_path = movie.poster_path,
                    release_date = movie.release_date,
                    vote_average = movie.vote_average
                )
                movieList.add(movieToRoom)
            }

            movieFlow.value = movieList.toList()

            // Retornamos la respuesta simulada
            return MovieResponse(
                page = 1,
                results = moviesFromApi
            )
        }


        override fun getPopularMoviesRoom(): Flow<List<Movie>> {
        return movieFlow.asStateFlow()        }

        override suspend fun deleteMovieRoom(movie: Movie) {
            movieList.remove(movie)
            movieFlow.value = movieList.toList()
        //    addMovieRoom(movie: Movie)
        }

        override suspend fun addMovieRoom(movie: Movie) {
            movieList.add(movie)
            movieFlow.value = movieList.toList()
        }

    }

}