package org.sopt.data.remote.api

import org.sopt.data.remote.model.request.ReqSignIn
import org.sopt.data.remote.model.request.ReqSignUp
import org.sopt.data.remote.model.response.ResSignIn
import org.sopt.data.remote.model.response.ResSignUp
import retrofit2.http.Body
import retrofit2.http.POST

interface UserService {
    //POST	회원가입
    @POST("/login/signup")
    suspend fun postSignUp(
            @Body body: ReqSignUp
    ): ResSignUp

    //POST	로그인
    @POST("/login/signin")
    suspend fun postSignIn(
            @Body body: ReqSignIn
    ): ResSignIn
}