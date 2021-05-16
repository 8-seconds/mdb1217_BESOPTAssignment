package org.sopt.data.remote.model.response

data class ResSignUp(
    val success: Boolean,
    val message: String,
    val data: Data
) {
    data class Data(
        val nickname: String
    )
}