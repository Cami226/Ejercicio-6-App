package com.bootcamp.ejercicio6app.repository


import com.bootcamp.ejercicio6app.datasource.RestDataSource
import com.bootcamp.ejercicio6app.model.MovieResponse
import com.bootcamp.ejercicio6app.model.Movie
import com.bootcamp.ejercicio6app.model.MovieDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
interface MovieRepository {
    suspend fun getPopularMoviesApi(): MovieResponse
    fun getPopularMoviesRoom(): Flow<List<Movie>>
}
class MovieRepositoryImpl @Inject constructor(
    private val restDataSource: RestDataSource,
    private val movieDao: MovieDao
) : MovieRepository {
    override suspend fun getPopularMoviesApi(): MovieResponse {

        val data = restDataSource.getPopularMovies()

        data.results.forEach {
            val movie = Movie(it.id ,it.title,
                it.overview, it.poster_path,
                it.release_date, it.vote_average)
            movieDao.insertMovieRoom(movie)
        }
        return data
    }
    override fun getPopularMoviesRoom(): Flow<List<Movie>> {
        return movieDao.getALlMoviesRoom()
    }
}
