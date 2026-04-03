package com.example.userDirectoryApp.data

import com.example.userDirectoryApp.network.UserApiService
import com.example.userDirectoryApp.network.UserResponse

interface UserProfilesRepository {
    suspend fun getPhotos(): UserResponse
}

class NetworkUserProfilesRepository(
    private val userApiService: UserApiService
) : UserProfilesRepository {
    override suspend fun getPhotos(): UserResponse = userApiService.getPhotos()
}