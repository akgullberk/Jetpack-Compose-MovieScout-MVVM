package com.example.moviescout.uix.view

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.util.Log
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation
import com.bumptech.glide.load.resource.bitmap.TransformationUtils
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.example.moviescout.data.entity.Film
import com.example.moviescout.data.entity.FilmLine
import com.example.moviescout.uix.viewmodel.AnasayfaViewModel
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.glide.GlideImage
import dev.chrisbanes.snapper.ExperimentalSnapperApi
import dev.chrisbanes.snapper.rememberSnapperFlingBehavior
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class, ExperimentalSnapperApi::class)
@Composable
fun Anasayfa(anasayfaViewModel: AnasayfaViewModel){
    val configuration = LocalConfiguration.current
    val ekranYuksekligi = configuration.screenHeightDp
    val ekranGenisligi = configuration.screenWidthDp

    var selectedItem by remember { mutableStateOf(0) }

    // Observe the news list and error message from the ViewModel
    val movieSuggestList by anasayfaViewModel.movieSuggestList.observeAsState(emptyList())
    val errorMessage by anasayfaViewModel.errorMessage.observeAsState("")

    // Kaydırma durumunu hatırlamak için bir ScrollState kullanıyoruz
    val scrollState = rememberScrollState()

    // Fetch news whenever the selected category changes
    LaunchedEffect(Unit) {
        anasayfaViewModel.fetchMovieSuggest()
    }



    Scaffold(
        containerColor = Color.Transparent,
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
        // Ana arayüz alanı
        Column(modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .padding(0.dp)
            .background(color = Color.Black)
            ,
        ) {
            FilmLazyRow(movieSuggestList = movieSuggestList)
        }
    }
}



@Composable
fun BlurredImageExample(imageUrl: String) {
    Box(modifier = Modifier
        .fillMaxWidth()
        .height(550.dp)
    ) {
        // Ana resim (bulanık olmayan kısım)
        GlideImage(
            imageModel = { imageUrl },
            imageOptions = ImageOptions(
                contentScale = ContentScale.Crop,
                alignment = Alignment.Center
            ),
            modifier = Modifier.fillMaxSize(),
            requestBuilder = {
                Glide.with(LocalContext.current)
                    .load(imageUrl)
                    .apply(RequestOptions.bitmapTransform(jp.wasabeef.glide.transformations.BlurTransformation(20, 1)))
            }
        )

        // Bulanıklık efekti ve gradient mask
        Canvas(modifier = Modifier.fillMaxSize()) {
            val gradientBrush = Brush.verticalGradient(
                colors = listOf(
                    Color.Transparent, // Üst kısmı net
                    Color.Black.copy(alpha = 0.4f), // Ortada hafif bulanıklık
                    Color.Black.copy(alpha = 0.9f), // Daha bulanık

                )
            )
            drawRect(brush = gradientBrush)
        }
    }
}

@Composable
fun FilmItem(film: Film) {
    val configuration = LocalConfiguration.current
    val ekranYuksekligi = configuration.screenHeightDp
    val ekranGenisligi = configuration.screenWidthDp

    film.data.lines.forEach { line ->

        Box(
            modifier = Modifier
                .fillMaxSize()
            ,
            contentAlignment = Alignment.Center
        ) {
            Column(
                modifier = Modifier
                ,
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Image as the background
                GlideImage(
                    imageModel = { line.img }, // loading a network image using an URL.
                    imageOptions = ImageOptions(
                        // Resmi kutuya tam sığdır
                        contentScale = ContentScale.Fit,
                        alignment = Alignment.Center
                    ),
                    modifier = Modifier
                        .width(400.dp)
                        .height(420.dp)
                        .padding(top = 50.dp)

                )
                Log.d("API_LOG", "Image URL: ${line.img}")

                Box(
                    modifier = Modifier

                        .width(410.dp)
                        .height(50.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Box(
                        modifier = Modifier
                        ,
                        contentAlignment = Alignment.Center // Hem yatay hem dikey ortalama

                    ) {

                        Row(
                            modifier = Modifier
                                .fillMaxWidth() // Row'u tam genişliğe yay
                            ,
                            horizontalArrangement = Arrangement.SpaceEvenly // Öğeler arasında eşit boşluk
                        ) {
                            Text(
                                text = line.sty.substringAfter("Tür:"), // "Tür:" ifadesinden sonrasını al
                                color = Color.White
                            )
                            Text(
                                text = line.year
                                    .substringAfter("Yapım:") // "Yapım:" ifadesinden sonrasını al
                                    .substringBefore(" - "),  // " - " ifadesinden öncesini al
                                color = Color.White
                            )
                            Text(text = line.times.substringAfter("Süre:"), color = Color.White)
                        }
                    }
                }
            }
        }


    }
}

@OptIn(ExperimentalSnapperApi::class)
@Composable
fun FilmLazyRow(movieSuggestList: List<Film>) {
    val listState = rememberLazyListState()
    val snapperFlingBehavior = rememberSnapperFlingBehavior(lazyListState = listState)

    // Track the current item index
    var currentItemIndex by remember { mutableStateOf(0) }

    // Update current item index when the item changes
    LaunchedEffect(listState.firstVisibleItemIndex) {
        currentItemIndex = listState.firstVisibleItemIndex + 1
    }

    // Get the background image URL based on the current item
    val backgroundImageUrl = movieSuggestList.getOrNull(currentItemIndex)?.data?.lines?.firstOrNull()?.img ?: ""

    // Yalnızca 7 film al
    val limitedMovieSuggestList = movieSuggestList.take(7)

    Box(modifier = Modifier.fillMaxSize()) {
        // Set background image dynamically as you scroll
        if (backgroundImageUrl.isNotEmpty()) {
            BlurredImageExample(imageUrl = backgroundImageUrl)
        } else {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.DarkGray) // Default color if no image URL
            )
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 16.dp), // Page Indicator için boşluk bırak
            verticalArrangement = Arrangement.Bottom
        ) {
            // Film LazyRow
            LazyRow(
                state = listState,
                flingBehavior = snapperFlingBehavior,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(500.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                items(limitedMovieSuggestList) { film ->
                    FilmItem(film = film)
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Çizgi şeklindeki Page Indicator
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                repeat(limitedMovieSuggestList.size) { index ->
                    Box(
                        modifier = Modifier
                            .padding(horizontal = 2.dp)
                            .height(4.dp) // Çizgi yüksekliği
                            .width(24.dp) // Tüm çubuklar aynı genişlikte
                            .background(
                                color = if (index == currentItemIndex) Color(0xFFFFD700) else Color.White,
                                shape = RoundedCornerShape(50) // Yuvarlatılmış köşeler
                            )
                    )
                }
            }
        }
    }
}