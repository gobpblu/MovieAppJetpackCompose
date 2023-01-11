package com.example.movieapp.screens.details

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import coil.transform.CircleCropTransformation
import com.example.movieapp.model.Movie
import com.example.movieapp.model.getMovies
import com.example.movieapp.widgets.MovieRow

@Composable
fun DetailsScreen(navController: NavController, movieId: String?) {

    val movie = getMovies().first { it.id == movieId }

    Scaffold(
        topBar = {
            TopAppBar(backgroundColor = Color.Gray,
                elevation = 5.dp) {
                Row(horizontalArrangement = Arrangement.Start) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Arrow Back",
                        modifier = Modifier
                            .clip(RoundedCornerShape(10.dp))
                            .clickable {
                                navController.popBackStack()
                            }
                            .padding(4.dp)
                    )
                    Spacer(Modifier.width(100.dp))
                Text(text = "Movie Details", modifier = Modifier.padding(4.dp))
                }
            }
        }
    ) {
        Surface(modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth()) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {
                MovieRow(movie)
                Spacer(modifier = Modifier.height(8.dp))
                Divider()
                Text(text = "Movie Images")
                HorizontalScrollableImages(movie)
            }
        }
            }
    }

@Composable
private fun HorizontalScrollableImages(movie: Movie) {
    LazyRow {
        items(movie.images) { image ->
            Card(modifier = Modifier
                .padding(12.dp)
                .size(240.dp),
                elevation = 5.dp) {
                AsyncImage(model = ImageRequest.Builder(LocalContext.current)
                    .data(image)
                    .crossfade(true)
                    .build(), contentDescription = "Movie Image")
            }
        }
    }
}