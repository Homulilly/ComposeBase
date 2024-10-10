package com.example.marsphoto

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import com.example.marsphoto.ui.HomeScreen
import com.example.marsphoto.ui.theme.MarsPhotoTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MarsPhotoTheme {
                MarsPhotosApp()
            }
        }
    }
}

@Composable
fun MarsPhotosApp(){
    HomeScreen()
}