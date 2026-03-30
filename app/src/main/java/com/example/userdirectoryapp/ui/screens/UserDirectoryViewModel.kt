/*
 * Copyright (C) 2023 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.userDirectoryApp.ui.screens

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.userDirectoryApp.network.DirectoryApi
import kotlinx.coroutines.launch
import java.io.IOException

sealed interface UserUiState {
    data class Success(val photos: String) : UserUiState
    object Error : UserUiState
    object Loading : UserUiState
}
class UserDirectoryViewModel : ViewModel() {
    /** The mutable State that stores the status of the most recent request */
    var userUiState: UserUiState by mutableStateOf(UserUiState.Loading)
        private set

    /**
     * Call getUserPhotos() on init so we can display status immediately.
     */
    init {
        getUserPhotos()
    }

    /**
     * Gets User photos information from the User API Retrofit service and updates the
     * [UserPhoto] [List] [MutableList].
     */
    private fun getUserPhotos() {
        viewModelScope.launch {
            /*userUiState = */try {
                val listResult = DirectoryApi.retrofitService.getPhotos()
                userUiState = UserUiState.Success(
                    "Success: ${listResult.users.size} User photos retrieved")
            }catch (e: IOException) {
                userUiState = UserUiState.Error
            }
        }
    }
}

