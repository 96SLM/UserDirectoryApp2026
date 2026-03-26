package com.example.userDirectoryApp.network

import kotlinx.serialization.Serializable

@Serializable
data class UserResponce(
    val total: Int,
    val users: List<User>
)

@Serializable
data class User(
    val id: Int,
    val firstName: String,
    val lastName: String,
    val email: String,
    val image: String
)
