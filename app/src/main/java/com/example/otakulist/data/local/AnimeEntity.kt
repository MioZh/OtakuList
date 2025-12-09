package com.example.otakulist.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
// TODO: add more fields if needed
@Entity(tableName = "favourites")
data class AnimeEntity(
    @PrimaryKey val id: Int,
    val title: String,
    val imageUrl: String?,
    val score: Double?
)
