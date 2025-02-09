package com.example.moviescout.uix.view

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.moviescout.uix.viewmodel.AnasayfaViewModel

@Composable
fun SayfaGecisleri(anasayfaViewModel: AnasayfaViewModel){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "anasayfa"){

        composable("anasayfa"){
            Anasayfa(anasayfaViewModel)
        }

    }
}