package org.sopt.di

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import org.sopt.data.local.SOPTSharedPreference

@HiltAndroidApp
class ApplicationController : Application() {
    companion object {
        lateinit var INSTANCE: ApplicationController
    }

    override fun onCreate() {
        super.onCreate()
        INSTANCE = this
        SOPTSharedPreference.init(applicationContext)
    }
}