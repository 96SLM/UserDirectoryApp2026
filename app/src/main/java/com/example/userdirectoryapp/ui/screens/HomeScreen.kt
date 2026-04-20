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

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.userDirectoryApp.R
import com.example.userDirectoryApp.network.User
import com.example.userDirectoryApp.network.UserResponse
import com.example.userDirectoryApp.ui.theme.UserDirectoryTheme

@Composable
fun UserProfileCard(profile: UserResponse, modifier: Modifier = Modifier) {
    ElevatedCard(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        ),
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .padding(8.dp)
    ) {
        Row {
            Box {
                AsyncImage(
                    //profile image
                    model = ImageRequest.Builder(context = LocalContext.current)
                        .data(profile.users[0].image)
                        .crossfade(true)
                        .build(),
                    error = painterResource(R.drawable.ic_broken_image),
                    placeholder = painterResource(R.drawable.loading_img),
                    contentDescription = stringResource(R.string.placeholder_result),
                    contentScale = ContentScale.Crop
                    ,modifier = modifier
                        .size(50.dp)
                        .aspectRatio(1f)
                )
            }
             Column {
                 Row() {
                     Text(
                         //First name line
//                         model = ImageRequest.Builder(context = LocalContext.current)
//                             .data(profile.users[0].firstName)
//                             .crossfade(true)
//                             .build(),
                         text = profile.users[0].firstName + profile.users[0].lastName,
                         modifier = Modifier.padding(
                             start = 16.dp,
                             top = 16.dp,
                             bottom = 16.dp
                         )
                             .wrapContentWidth()
                     )
//                     Text(
//                         //Last name line
////                         model = ImageRequest.Builder(context = LocalContext.current)
////                             .data(profile.users[0].lastName)
////                             .crossfade(true)
////                             .build(),
//                         text = profile.users[0].lastName,
//
//                     )
                 }
                 Text(
                     //Email address line
                     text = profile.users[0].email,
                     modifier = Modifier.padding( start = 16.dp)
                 )
             }
        }
    }
}

@Composable
fun HomeScreen(
    userUiState: UserUiState,
    modifier: Modifier = Modifier
) {
    when (userUiState) {
        is UserUiState.Loading -> LoadingScreen(modifier = modifier.fillMaxSize())
        is UserUiState.Success -> UserProfileCard(profile = userUiState.profile, modifier.fillMaxSize())
        is UserUiState.Error -> ErrorScreen( modifier = modifier.fillMaxSize())
    }
}

@Composable
fun LoadingScreen(modifier: Modifier = Modifier) {
    Image(
        modifier = modifier.size(200.dp),
        painter = painterResource(R.drawable.loading_img),
        contentDescription = stringResource(R.string.loading)
    )
}

@Composable
fun ErrorScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_connection_error), contentDescription = ""
        )
        Text(text = stringResource(R.string.loading_failed), modifier = Modifier.padding(16.dp))
    }
}
/**
 * ResultScreen displaying number of photos retrieved.
 */
@Composable
fun ResultScreen(photos: String, modifier: Modifier = Modifier) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
    ) {
        Text(text = photos)
    }
}

@Preview(showBackground = true)
@Composable
fun ResultScreenPreview() {
    UserDirectoryTheme {
        ResultScreen(stringResource(R.string.placeholder_result))
    }
}

@Preview
@Composable
fun UserProfileCardPreview() {
    UserDirectoryTheme {
        val profile = null
        UserProfileCard(profile = profile)
    }
}
