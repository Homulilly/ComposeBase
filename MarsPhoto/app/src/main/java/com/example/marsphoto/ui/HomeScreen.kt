package com.example.marsphoto.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
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
        is MarsUiState.Success -> CoilScreen(
            photo = marsUiState.photo,
            modifier = modifier.fillMaxSize().verticalScroll(rememberScrollState())
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
    marsUiState: MarsUiState,
    modifier: Modifier = Modifier
){
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
    ) {
        Text(text = marsUiState.toString())
    }
}

@Composable
fun CoilScreen(
    photo: MarsPhoto,
    modifier: Modifier = Modifier
){
    Card(modifier = modifier.padding(16.dp)) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(photo.imgSrc)
                .crossfade(true)
                .build(),
            contentDescription = null,
            contentScale = ContentScale.Crop,
        )
    }
}