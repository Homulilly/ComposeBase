package com.example.marsphoto.fake

import com.example.marsphoto.data.MarsPhotosRepository
import com.example.marsphoto.network.MarsPhoto

class FakeNetworkMarsPhotosRepository : MarsPhotosRepository {
    override suspend fun getMarsPhotos(): List<MarsPhoto> {
        return FakeDataSource.photosList
    }
}