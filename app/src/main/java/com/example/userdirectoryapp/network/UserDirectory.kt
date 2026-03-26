package com.example.userDirectoryApp.network

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserDirectory(
    val id: String,
    //manual mapping of img_src name
    @SerialName(value = "img_src")
    val imgSrc: String
)