package com.example.userDirectoryApp.data

import com.example.userDirectoryApp.network.UserApiService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

interface AppContainer {
    val userProfilesRepository: UserProfilesRepository
}

class DefaultAppContainer : AppContainer {

    private val baseUrl =
        "https://dummyjson.com"

    private val json = Json {
        ignoreUnknownKeys = true
    }
    private val retrofit = Retrofit.Builder()
        .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
        .baseUrl(baseUrl)
        .build()

    private val retrofitService : UserApiService by lazy {
        retrofit.create(UserApiService::class.java)
    }

    override val userProfilesRepository: UserProfilesRepository by lazy {
        NetworkUserProfilesRepository(retrofitService)
    }
}
