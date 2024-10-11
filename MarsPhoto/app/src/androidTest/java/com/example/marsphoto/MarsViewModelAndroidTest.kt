package com.example.marsphoto

import com.example.marsphoto.fake.FakeDataSource
import com.example.marsphoto.fake.FakeNetworkMarsPhotosRepository
import com.example.marsphoto.ui.MarsUiState
import com.example.marsphoto.ui.MarsViewModel
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Test

class MarsViewModelAndroidTest (){
    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun marsViewModel_getMarsPhotos_verifyMarsUiStateSuccess() =
        runTest {
            val viewModel = MarsViewModel(
                marsPhotosRepository = FakeNetworkMarsPhotosRepository()
            )

            //让测试等待协程执行完毕
            advanceUntilIdle()

            assertEquals(
                MarsUiState.Success("Success: ${FakeDataSource.photosList.size} Mars photos retrieved"),
                viewModel.marsUiState
            )
        }
}