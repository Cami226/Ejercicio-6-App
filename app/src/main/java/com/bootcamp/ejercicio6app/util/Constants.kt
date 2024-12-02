package com.bootcamp.ejercicio6app.util

import com.bootcamp.ejercicio6app.BuildConfig


class Constants {
    companion object {
        const val BASE_URL = "https://api.themoviedb.org/3/"
        const val ENDPOINT = "movie/popular"
        const val API_KEY = BuildConfig.api_key
    }
}

    //https://api.themoviedb.org/3/movie/popular?api_key=2446fecd5ebfeb2e3b6e33a9824b255b