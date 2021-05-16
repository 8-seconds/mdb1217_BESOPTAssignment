package org.sopt.data.repository

import org.sopt.data.remote.model.response.ResFollower

interface GitRepo {
    suspend fun getFollower (
        path: String
    ): List<ResFollower>
}