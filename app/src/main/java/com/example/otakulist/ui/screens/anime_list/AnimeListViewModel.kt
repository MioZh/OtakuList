package com.example.otakulist.ui.screens.anime_list

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.otakulist.data.repository.AnimeRepository
import com.example.otakulist.model.Anime
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
class AnimeListViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = AnimeRepository(application)

    private val _animeList = MutableStateFlow<List<Anime>>(emptyList())
    val animeList: StateFlow<List<Anime>> = _animeList

    fun loadAnime() {
        viewModelScope.launch {
            try {
                _animeList.value = repository.getAnimeList()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}

