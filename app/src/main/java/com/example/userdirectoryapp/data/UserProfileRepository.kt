package com.example.userDirectoryApp.data

import com.example.userDirectoryApp.network.DirectoryApi
import com.example.userDirectoryApp.network.UserResponse

interface UserProfilesRepository {
    suspend fun getPhotos(): UserResponse
}

class NetworkUserProfilesRepository() : UserProfilesRepository{
    override suspend fun getPhotos(): UserResponse {
        return DirectoryApi.retrofitService.getPhotos()
    }
}