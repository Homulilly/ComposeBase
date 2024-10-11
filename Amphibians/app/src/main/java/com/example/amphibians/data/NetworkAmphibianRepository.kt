package com.example.amphibians.data

import com.example.amphibians.model.Amphibian
import com.example.amphibians.network.AmphibianApiServices
import javax.inject.Inject

class NetworkAmphibianRepository @Inject constructor(
    private val amphibianApiServices: AmphibianApiServices
) : AmphibianRepository {
    override suspend fun getAmphibians(): List<Amphibian> = amphibianApiServices.getAmphibians()
}