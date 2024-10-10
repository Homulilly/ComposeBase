package com.example.marsphoto.data

import com.example.marsphoto.network.MarsPhoto
import javax.inject.Inject

class LocalMarsPhotosRepository @Inject constructor() : MarsPhotosRepository{
    override suspend fun getMarsPhotos(): List<MarsPhoto> {
        return listOf(
            MarsPhoto(
                id = 424905,
                imgSrc = "https://mars.jpl.nasa.gov/msl-raw-images/msss/01000/mcam/1000MR0044631300503690E01_DXXX.jpg"
            ),
            MarsPhoto(
                id = 424906,
                imgSrc = "https://mars.jpl.nasa.gov/msl-raw-images/msss/01000/mcam/1000ML0044631300305227E03_DXXX.jpg"
            ),
            MarsPhoto(
                id = 424907,
                imgSrc = "https://mars.jpl.nasa.gov/msl-raw-images/msss/01000/mcam/1000MR0044631300503690E01_DXXX.jpg"
            ),
            MarsPhoto(
                id = 424908,
                imgSrc = "https://mars.jpl.nasa.gov/msl-raw-images/msss/01000/mcam/1000ML0044631290305226E03_DXXX.jpg"
            )
        )
    }
}