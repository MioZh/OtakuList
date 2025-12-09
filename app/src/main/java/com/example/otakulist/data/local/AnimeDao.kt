package com.example.otakulist.data.local

import androidx.room.*

@Dao
interface AnimeDao {

    @Query("SELECT * FROM favourites")
    suspend fun getFavourites(): List<AnimeEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addToFavourites(anime: AnimeEntity)

    @Delete
    suspend fun removeFromFavourites(anime: AnimeEntity)

    @Query("DELETE FROM favourites")
    suspend fun clearFavourites()
}
