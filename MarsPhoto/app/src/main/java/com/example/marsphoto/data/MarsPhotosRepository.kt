package com.example.marsphoto.data

import com.example.marsphoto.network.MarsApi
import com.example.marsphoto.network.MarsPhoto

interface MarsPhotosRepository {
    suspend fun getMarsPhotos(): List<MarsPhoto>
}

class NetWorkMarsPhotosRepository : MarsPhotosRepository{
    override suspend fun getMarsPhotos(): List<MarsPhoto> {
        return MarsApi.retrofitService.getPhotos()
    }
}