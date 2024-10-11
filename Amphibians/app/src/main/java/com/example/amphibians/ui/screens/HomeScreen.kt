package com.example.amphibians.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.amphibians.model.Amphibian

@Composable
fun HomeScreen(){
    val ampViewModel: AmphibianViewModel = viewModel()
    val uiState = ampViewModel.ampUiState
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ){
        when(uiState) {
            is AmpUiState.Error -> { ErrorScreen(modifier = Modifier.fillMaxSize()) }
            is AmpUiState.Loading ->  { LoadingScreen(modifier = Modifier.fillMaxSize()) }
            is AmpUiState.Success -> {
                SuccessScreen(
                    uiState.amphibiansList
                )
            }
        }

    }
}

@Composable
fun LoadingScreen(
    modifier: Modifier = Modifier
){
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
    ){
        Text("Loading")
    }
}

@Composable
fun ErrorScreen(
    modifier: Modifier = Modifier
){
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
    ){
        Text("Meet Errors")
    }
}

@Composable
fun SuccessScreen(
    amphibians: List<Amphibian>,
    modifier: Modifier = Modifier
){
    LazyColumn {
        itemsIndexed(amphibians){ _ , item ->
            AmphibianCard(item)
        }
    }
}

@Composable
fun AmphibianCard(
    amphibian: Amphibian,
    modifier: Modifier = Modifier
){
    Card(
        modifier = modifier
    ){
        Column(
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "${amphibian.name} (${amphibian.type})",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Start,
                modifier = Modifier.padding(8.dp)
            )

            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(amphibian.imgSrc)
                    .crossfade(true)
                    .build(),
                contentDescription = amphibian.description,
                contentScale = ContentScale.FillWidth,
                modifier = Modifier.fillMaxWidth(),
            )

            Text(
                text = amphibian.description,
                style = MaterialTheme.typography.bodyMedium,
                textAlign = TextAlign.Justify,
                modifier = Modifier.padding(8.dp)
            )
        }
    }
}