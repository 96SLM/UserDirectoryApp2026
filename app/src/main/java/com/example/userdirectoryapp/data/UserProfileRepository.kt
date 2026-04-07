package com.example.userDirectoryApp.data

import com.example.userDirectoryApp.network.UserApiService
import com.example.userDirectoryApp.network.UserResponse

interface UserProfilesRepository {
    suspend fun getUserPhotos(): UserResponse
}

class NetworkUserProfilesRepository(
    private val userApiService: UserApiService
) : UserProfilesRepository {
    override suspend fun getUserPhotos(): UserResponse = userApiService.getUserPhotos()
}