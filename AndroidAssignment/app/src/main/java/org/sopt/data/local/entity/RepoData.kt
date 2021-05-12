package org.sopt.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "repo_data_table")
data class RepoData(
        @PrimaryKey(autoGenerate = true)
        val id: Long?,

        @ColumnInfo(name = "name")
        val name: String,

        @ColumnInfo(name = "explanation")
        val explanation: String,

        @ColumnInfo(name = "language")
        val language: String,

        @ColumnInfo(name = "type")
        val type: Int,

        @ColumnInfo(name = "star")
        val star: Boolean
)