package com.example.otakulist.data.repository

import android.content.Context
import com.example.otakulist.data.local.AnimeDao
import com.example.otakulist.data.local.AnimeEntity
import com.example.otakulist.data.local.DatabaseProvider
import com.example.otakulist.data.remote.RetrofitInstance
import com.example.otakulist.model.Anime
import com.example.otakulist.model.toAnime

class AnimeRepository(context: Context) {

    private val dao: AnimeDao = DatabaseProvider.getDatabase(context).animeDao()

    suspend fun getAnimeList(): List<Anime> {
        val response = RetrofitInstance.api.getAnimeList()
        return response.data.map { it.toAnime() }
    }

    suspend fun getAnimeDetails(id: Int): Anime {
        val response = RetrofitInstance.api.getAnimeDetails(id)
        return response.data.toAnime()
    }

    suspend fun getFavourites(): List<AnimeEntity> {
        return dao.getFavourites()
    }

    suspend fun addToFavourites(anime: Anime) {
        dao.addToFavourites(
            AnimeEntity(
                id = anime.id,
                title = anime.title,
                imageUrl = anime.imageUrl,
                score = anime.score
            )
        )
    }

    suspend fun removeFromFavourites(entity: AnimeEntity) {
        dao.removeFromFavourites(entity)
    }

    suspend fun clearFavourites() {
        dao.clearFavourites()
    }
}
