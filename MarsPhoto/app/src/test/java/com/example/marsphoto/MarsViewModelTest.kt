package com.example.marsphoto

import com.example.marsphoto.fake.FakeDataSource
import com.example.marsphoto.fake.FakeNetworkMarsPhotosRepository
import com.example.marsphoto.rules.TestDispatcherRule
import com.example.marsphoto.ui.MarsUiState
import com.example.marsphoto.ui.MarsViewModel
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

class MarsViewModelTest {

    @get:Rule
    val testDispatcher = TestDispatcherRule()

    @Test
    fun marsViewModel_getMarsPhotos_verifyMarsUiStateSuccess() =
        runTest {
            val marsViewModel = MarsViewModel(
                marsPhotosRepository = FakeNetworkMarsPhotosRepository()
            )
            assertEquals(
                MarsUiState.Success("Success: ${FakeDataSource.photosList.size} Mars " +
                        "photos retrieved"),
                marsViewModel.marsUiState
            )
        }
}