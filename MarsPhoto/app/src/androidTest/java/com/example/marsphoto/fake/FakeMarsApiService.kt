package com.example.marsphoto.fake

import com.example.marsphoto.network.MarsApiService
import com.example.marsphoto.network.MarsPhoto

class FakeMarsApiService: MarsApiService{
    override suspend fun getPhotos(): List<MarsPhoto> = FakeDataSource.photosList
}