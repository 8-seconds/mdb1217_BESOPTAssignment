package org.sopt.data.remote.model.response

data class ResSignIn(
    val success: Boolean,
    val message: String,
    val data: Data
) {
    data class Data(
        val UserId: Int,
        val user_nickname: String,
        val token: String
    )
}