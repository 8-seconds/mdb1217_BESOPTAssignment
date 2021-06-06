package org.sopt.data.remote.datasource

import org.sopt.data.remote.api.UserService
import org.sopt.data.remote.model.request.ReqSignIn
import org.sopt.data.remote.model.request.ReqSignUp
import org.sopt.data.remote.model.response.ResSignIn
import org.sopt.data.remote.model.response.ResSignUp
import retrofit2.Response
import javax.inject.Inject

class UserDataSourceImpl @Inject constructor(private val userService: UserService) : UserDataSource {
    override suspend fun postSignIn(body: ReqSignIn): ResSignIn = userService.postSignIn(body)
    override suspend fun postSignUp(body: ReqSignUp): ResSignUp = userService.postSignUp(body)
}