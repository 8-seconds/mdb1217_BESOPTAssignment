package org.sopt.data.repository

import org.sopt.data.remote.datasource.UserDataSource
import org.sopt.data.remote.model.request.ReqSignIn
import org.sopt.data.remote.model.request.ReqSignUp
import org.sopt.data.remote.model.response.ResSignIn
import org.sopt.data.remote.model.response.ResSignUp
import javax.inject.Inject

class UserRepoImpl @Inject constructor(private val userDataSource: UserDataSource) : UserRepo {
    override suspend fun postSignUp(body: ReqSignUp): ResSignUp = userDataSource.postSignUp(body)
    override suspend fun postSignIn(body: ReqSignIn): ResSignIn = userDataSource.postSignIn(body)
}