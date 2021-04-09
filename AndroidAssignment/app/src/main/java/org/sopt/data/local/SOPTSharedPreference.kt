package org.sopt.data.local

import android.content.Context
import android.content.SharedPreferences

object SOPTSharedPreference {
    private const val AUTO_LOGIN = "AUTO_LOGIN"
    private const val ID_PASSWORD_EXIST = "ID_PASSWORD_EXIST"
    private const val USER_NAME = "USER_NAME"

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

    fun getIdPasswordExist(): Boolean {
        return preferences.getBoolean(ID_PASSWORD_EXIST, false)
    }

    fun setIdPasswordExist(value: Boolean) {
        preferences.edit().putBoolean(ID_PASSWORD_EXIST, value).apply()
    }

    fun getName(): String? {
        return preferences.getString(USER_NAME, "")
    }

    fun setName(value: String) {
        preferences.edit().putString(USER_NAME, value).apply()
    }
}