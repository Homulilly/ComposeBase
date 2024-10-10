package com.example.marsphoto.data

import com.example.marsphoto.network.MarsPhoto

interface MarsPhotosRepository {
    suspend fun getMarsPhotos(): List<MarsPhoto>
}

