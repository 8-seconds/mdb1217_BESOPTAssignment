package org.sopt.data.remote.model.request

import com.google.gson.annotations.SerializedName

data class ReqSignIn(
    @SerializedName("email")
    val email: String,
    @SerializedName("password")
    val password: String
)