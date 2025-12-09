package com.example.otakulist.ui.screens.anime_details

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.otakulist.data.local.AnimeEntity
import com.example.otakulist.data.repository.AnimeRepository
import com.example.otakulist.model.Anime
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class AnimeDetailsViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = AnimeRepository(application)

    private val _anime = MutableStateFlow<Anime?>(null)
    val anime: StateFlow<Anime?> = _anime

    private val _isFavourite = MutableStateFlow(false)
    val isFavourite: StateFlow<Boolean> = _isFavourite

    fun loadAnime(animeId: Int) {
        viewModelScope.launch {

            // Загружаем данные о тайтле из API
            val result = repository.getAnimeDetails(animeId)
            _anime.value = result

            // Проверяем избранность
            val favourites = repository.getFavourites()
            _isFavourite.value = favourites.any { it.id == animeId }
        }
    }

    fun toggleFavourite() {
        val item = _anime.value ?: return

        viewModelScope.launch {
            if (_isFavourite.value) {
                // удалить
                repository.removeFromFavourites(
                    AnimeEntity(
                        id = item.id,
                        title = item.title,
                        imageUrl = item.imageUrl,
                        score = item.score
                    )
                )
                _isFavourite.value = false

            } else {
                // добавить
                repository.addToFavourites(item)
                _isFavourite.value = true
            }
        }
    }
}
