package com.example.marsphoto

import com.example.marsphoto.data.NetWorkMarsPhotosRepository
import com.example.marsphoto.fake.FakeDataSource
import com.example.marsphoto.fake.FakeMarsApiService
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.test.runTest
import org.junit.Test

class NetworkMarsRepositoryTest {
    @Test
    fun networkMarsPhotoRepository_getMarsPhotos_verifyPhotoList() =
        runTest {
            val repository = NetWorkMarsPhotosRepository(
                marsApiService = FakeMarsApiService()
            )
                assertEquals(FakeDataSource.photosList, repository.getMarsPhotos())
        }
}