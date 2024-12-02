package com.bootcamp.ejercicio6app.datasource


import com.bootcamp.ejercicio6app.model.MovieResponse
import com.bootcamp.ejercicio6app.util.Constants.Companion.API_KEY
import com.bootcamp.ejercicio6app.util.Constants.Companion.ENDPOINT
import retrofit2.http.GET
import retrofit2.http.Query
interface RestDataSource {
    @GET(ENDPOINT)
    suspend fun getPopularMovies(
        @Query("api_key") apiKey: String = API_KEY
    ): MovieResponse
}