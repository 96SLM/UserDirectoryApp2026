package com.example.userDirectoryApp.network

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.http.GET

private const val BASE_URL =
   "https://dummyjson.com"

private val json = Json {
    ignoreUnknownKeys = true
}
private val retrofit = Retrofit.Builder()
    .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
    .baseUrl(BASE_URL)
    .build()

interface UserApiService {
    @GET ("users?limit=20")
    suspend fun getPhotos(): /*List<UserDirectory>*/ UserResponse
}

object DirectoryApi {
    val retrofitService : UserApiService by lazy {
        retrofit.create(UserApiService::class.java)
    }
}