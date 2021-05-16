package org.sopt.data.remote.model.request

import com.google.gson.annotations.SerializedName

data class ReqSignUp(
    @SerializedName("email")
    val email: String,
    @SerializedName("password")
    val password: String,
    @SerializedName("sex")
    val sex: String,
    @SerializedName("nickname")
    val nickname: String,
    @SerializedName("phone")
    val phone: String,
    @SerializedName("birth")
    val birth: String
)