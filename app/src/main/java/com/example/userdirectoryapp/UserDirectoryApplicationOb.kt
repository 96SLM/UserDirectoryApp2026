package com.example.userDirectoryApp

import android.app.Application
import com.example.userDirectoryApp.data.AppContainer
import com.example.userDirectoryApp.data.DefaultAppContainer

class UserDirectoryApplicationOb : Application {
    lateinit var container: AppContainer
    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer()
}