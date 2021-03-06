package org.sopt.data.repository

import org.sopt.data.remote.model.request.ReqSignIn
import org.sopt.data.remote.model.request.ReqSignUp
import org.sopt.data.remote.model.response.ResSignIn
import org.sopt.data.remote.model.response.ResSignUp

interface UserRepo {
    suspend fun postSignUp (
        body: ReqSignUp
    ): ResSignUp

    suspend fun postSignIn (
        body: ReqSignIn
    ): ResSignIn
}