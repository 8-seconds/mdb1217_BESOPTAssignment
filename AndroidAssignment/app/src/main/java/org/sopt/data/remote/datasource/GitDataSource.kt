package org.sopt.data.remote.datasource

import org.sopt.data.remote.model.response.ResFollower

interface GitDataSource {
    suspend fun getFollower(path: String): List<ResFollower>
}