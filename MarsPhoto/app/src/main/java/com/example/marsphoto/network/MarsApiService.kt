package com.example.marsphoto.network

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.http.GET

private val BASE_URL =
    "https://android-kotlin-fun-mars-server.appspot.com"

// 创建 Retrofit 对象
private val retrofit = Retrofit.Builder()
    .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
    .baseUrl(BASE_URL)
    .build()

// 定义 MarsApiService 的接口，该接口定义 Retrofit 如何使用 HTTP 请求与网络服务器通信。
interface MarsApiService{
    @GET("photos")
    suspend fun getPhotos(): List<MarsPhoto>
}

// 声明单例对象。单例模式可确保对于一个对象只创建一个实例。
object MarsApi{
    val retrofitService: MarsApiService by lazy{
        retrofit.create(MarsApiService::class.java)
    }
}