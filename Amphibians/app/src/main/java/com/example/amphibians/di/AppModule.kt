package com.example.amphibians.di

import com.example.amphibians.data.AmphibianRepository
import com.example.amphibians.data.NetworkAmphibianRepository
import com.example.amphibians.network.AmphibianApiServices
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import javax.inject.Singleton

private const val BASE_URL = "https://android-kotlin-fun-mars-server.appspot.com"

@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {

    @Binds
    @Singleton
    abstract fun bindsAmphibianRepository(
        networkAmphibianRepository: NetworkAmphibianRepository
//        localAmphibianRepository: LocalAmphibianRepository
    ) : AmphibianRepository

    companion object {
        @Provides
        @Singleton
        fun provideRetrofit() : Retrofit{
            return Retrofit.Builder()
                .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
                .baseUrl(BASE_URL)
                .build()
        }

        @Provides
        @Singleton
        fun provideAmphibianApiServices(retrofit : Retrofit): AmphibianApiServices{
            return retrofit.create(AmphibianApiServices::class.java)
        }
    }
}