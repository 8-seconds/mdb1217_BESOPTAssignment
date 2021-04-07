package org.sopt.data.local

import android.content.Context
import android.content.SharedPreferences

object SOPTSharedPreference {
    private const val AUTO_LOGIN = "AUTO_LOGIN"

    lateinit var preferences: SharedPreferences

    fun init(context: Context) {
        preferences = context.getSharedPreferences(context.packageName, Context.MODE_PRIVATE)
    }

    fun getAutoLogin(): Boolean {
        return preferences.getBoolean(AUTO_LOGIN, false)
    }

    fun setAutoLogin(value: Boolean) {
        preferences.edit().putBoolean(AUTO_LOGIN, value).apply()
    }
}