package org.sopt.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "profile_data_table")
data class ProfileData(
        @PrimaryKey(autoGenerate = true)
        val id: Long?,

        @ColumnInfo(name = "title")
        val title: String,

        @ColumnInfo(name = "explanation")
        val explanation: String,

        @ColumnInfo(name = "date")
        val date: String
)