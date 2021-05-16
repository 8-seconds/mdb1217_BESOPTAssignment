package org.sopt.data.remote.api

import org.sopt.data.remote.model.response.ResFollower
import retrofit2.http.GET
import retrofit2.http.Path

interface GitService {
    @GET("/users/{username}/followers")
    suspend fun geFollower(
        @Path("username") username:String
    ): List<ResFollower>
}