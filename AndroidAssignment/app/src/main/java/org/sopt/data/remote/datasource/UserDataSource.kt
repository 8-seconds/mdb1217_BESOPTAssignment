package org.sopt.data.remote.datasource

import org.sopt.data.remote.model.request.ReqSignIn
import org.sopt.data.remote.model.request.ReqSignUp
import org.sopt.data.remote.model.response.ResSignIn
import org.sopt.data.remote.model.response.ResSignUp

interface UserDataSource {
    suspend fun postSignIn(body: ReqSignIn): ResSignIn
    suspend fun postSignUp(body: ReqSignUp): ResSignUp
}