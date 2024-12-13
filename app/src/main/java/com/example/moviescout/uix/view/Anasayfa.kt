package com.example.moviescout.uix.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.moviescout.data.entity.Film
import com.example.moviescout.uix.viewmodel.AnasayfaViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Anasayfa(anasayfaViewModel: AnasayfaViewModel){
    var selectedItem by remember { mutableStateOf(0) }

    // Observe the news list and error message from the ViewModel
    val movieSuggestList by anasayfaViewModel.movieSuggestList.observeAsState(emptyList())
    val errorMessage by anasayfaViewModel.errorMessage.observeAsState("")

    // Fetch news whenever the selected category changes
    LaunchedEffect(Unit) {
        anasayfaViewModel.fetchMovieSuggest()
    }

    Scaffold(
        containerColor = Color(0xFF181c25),
        topBar = {
            TopAppBar(
                title = {},
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Black),
                navigationIcon = {
                    IconButton(onClick = {}) {
                        Icon(Icons.Filled.Menu, contentDescription = "Menu", tint = Color.White)
                    }
                },
                actions = {
                    IconButton(onClick = {}) {
                        Icon(Icons.Filled.Favorite, contentDescription = "Favorite",tint = Color.White)
                    }
                    IconButton(onClick = {}) {
                        Icon(Icons.Filled.Settings, contentDescription = "Settings",tint = Color.White)
                    }
                    IconButton(onClick = {}) {
                        Icon(Icons.Filled.AccountCircle, contentDescription = "Profile",tint = Color.White)
                    }
                }
            )

        },
        bottomBar = {
            BottomAppBar(
                containerColor = Color(0xFF0b0e13)
            ) {
                BottomNavigation(
                    backgroundColor = Color(0xFF0b0e13)
                ){
                    BottomNavigationItem(
                        icon = { Icon(Icons.Default.Home, contentDescription = "Home",tint = Color.White) },

                        selected = selectedItem == 0,
                        onClick = { selectedItem = 0 }
                    )
                    BottomNavigationItem(
                        icon = { Icon(Icons.Default.Favorite, contentDescription = "Favorites",tint = Color.White) },

                        selected = selectedItem == 1,
                        onClick = { selectedItem = 1 }
                    )
                    BottomNavigationItem(
                        icon = { Icon(Icons.Default.Settings, contentDescription = "Settings", tint = Color.White) },

                        selected = selectedItem == 2,
                        onClick = { selectedItem = 2 }
                    )
                }
            }
        }

    ) { paddingValues ->
        Column(
            modifier = Modifier.padding(paddingValues)
        ) {
            // Hata mesajı göster
            if (errorMessage.isNotEmpty()) {
                Text(text = errorMessage, color = Color.Red, modifier = Modifier.padding(16.dp))
            }

            // FilmLine nesnelerinin adlarını göster
            LazyColumn(modifier = Modifier.fillMaxWidth().padding(16.dp)) {
                items(movieSuggestList) { film ->
                    film.data.lines.forEach { line ->
                        Text(
                            text = line.name,
                            color = Color.White,
                            modifier = Modifier.padding(vertical = 8.dp)
                        )
                    }
                }
            }
        }
    }
}