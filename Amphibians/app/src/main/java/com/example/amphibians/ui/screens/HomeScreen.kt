package com.example.amphibians.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.amphibians.R
import com.example.amphibians.model.Amphibian
import androidx.compose.foundation.lazy.items

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier
){
    val ampViewModel: AmphibianViewModel = viewModel()
    val uiState = ampViewModel.ampUiState
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ){
        when(uiState) {
            is AmpUiState.Error -> { ErrorScreen(modifier = Modifier.fillMaxSize()) }
            is AmpUiState.Loading ->  { LoadingScreen(modifier = Modifier.fillMaxSize()) }
            is AmpUiState.Success -> { SuccessScreen(uiState.amphibiansList) }
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
//    LazyColumn {
//        itemsIndexed(amphibians){ _ , item ->
//            AmphibianCard(item)
//        }
//    }
    LazyColumn {
        items(amphibians) { amphibian ->
            AmphibianCard(
                amphibian = amphibian,
                modifier = Modifier.fillMaxSize().padding(8.dp)
            )
        }
    }
}

@Composable
fun AmphibianCard(
    amphibian: Amphibian,
    modifier: Modifier = Modifier
){
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(8.dp)
    ){
        Column {
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
                error = painterResource(id = R.drawable.ic_broken_image),
                placeholder = painterResource(id = R.drawable.loading_img),
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