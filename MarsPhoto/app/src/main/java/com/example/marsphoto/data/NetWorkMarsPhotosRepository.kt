package com.example.marsphoto.data

import com.example.marsphoto.network.MarsApiService
import com.example.marsphoto.network.MarsPhoto
import javax.inject.Inject

class NetWorkMarsPhotosRepository @Inject constructor(
    private val marsApiService: MarsApiService
) : MarsPhotosRepository{
    override suspend fun getMarsPhotos(): List<MarsPhoto> = marsApiService.getPhotos()
}