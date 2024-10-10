package com.example.marsphoto.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.marsphoto.network.MarsPhoto


@Composable
fun HomeScreen(
    modifier: Modifier = Modifier
) {
    val viewModel: MarsViewModel = viewModel()
    val marsUiState = viewModel.marsUiState
    when(marsUiState){
        is MarsUiState.Success -> SuccessScreen(
            photos = marsUiState.photos,
            modifier = modifier.fillMaxSize()
        )
        MarsUiState.Loading -> LoadingScreen(modifier = modifier.fillMaxSize())
        MarsUiState.Error -> ErrorScreen(modifier = modifier.fillMaxSize())
    }
}

@Composable
fun LoadingScreen(modifier: Modifier = Modifier) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
    ) {
        Text("Loading")
    }
}

@Composable
fun ErrorScreen(modifier: Modifier = Modifier) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
    ) {
        Text("Meet Error")
    }
}

@Composable
fun SuccessScreen(
    photos: List<MarsPhoto>,
    modifier: Modifier = Modifier) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
    ) {
        LazyVerticalGrid(columns = GridCells.Adaptive(minSize = 150.dp)) {
            itemsIndexed(photos){ _, photo ->
                CoilScreen(photo)
            }
        }
    }
}

@Composable
fun CoilScreen(
    photo: MarsPhoto,
    modifier: Modifier = Modifier
){
    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(photo.imgSrc)
            .crossfade(true)
            .build(),
        contentDescription = null,
        contentScale = ContentScale.Crop,
    )
}