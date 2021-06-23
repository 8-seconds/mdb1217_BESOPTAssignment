package org.sopt.data.local

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit

object SOPTSharedPreference {
    private const val AUTO_LOGIN = "AUTO_LOGIN"
    private const val USER_NAME = "USER_NAME"

    lateinit var preferences: SharedPreferences

    fun init(context: Context) {
        preferences = context.getSharedPreferences(context.packageName, Context.MODE_PRIVATE)
    }

    fun getAutoLogin(): Boolean = preferences.getBoolean(AUTO_LOGIN, false)

    fun setAutoLogin(value: Boolean) {
        preferences.edit{putBoolean(AUTO_LOGIN, value)}
    }

    fun getName(): String? = preferences.getString(USER_NAME, "")

    fun setName(value: String) {
        preferences.edit{putString(USER_NAME, value)}
    }

    fun clearStorage() {
        preferences.edit { clear() }
    }
}