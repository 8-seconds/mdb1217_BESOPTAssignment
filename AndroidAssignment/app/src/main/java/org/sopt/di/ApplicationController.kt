package org.sopt.di

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class ApplicationController : Application() {
    companion object {
        lateinit var INSTANCE: ApplicationController
    }

    override fun onCreate() {
        super.onCreate()
        INSTANCE = this
    }
}