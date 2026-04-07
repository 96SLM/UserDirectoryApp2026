package com.example.userDirectoryApp.network

import retrofit2.http.GET


interface UserApiService {
    @GET ("users?limit=20")
    suspend fun getUserPhotos(): UserResponse
}

