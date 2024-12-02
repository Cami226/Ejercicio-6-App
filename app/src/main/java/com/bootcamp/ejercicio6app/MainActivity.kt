package com.bootcamp.ejercicio6app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.bootcamp.ejercicio6app.ui.theme.Ejercicio6AppTheme

import com.bootcamp.ejercicio6app.view.HomeView
import com.bootcamp.ejercicio6app.viewModel.MovieViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //enableEdgeToEdge()
        val viewModel : MovieViewModel by viewModels()
        setContent {
            Ejercicio6AppTheme {
                HomeView(viewModel)
            }
        }
    }
}