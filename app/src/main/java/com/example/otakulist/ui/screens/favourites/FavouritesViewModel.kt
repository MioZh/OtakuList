package com.example.otakulist.ui.screens.favourites

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.otakulist.data.repository.AnimeRepository
import com.example.otakulist.data.local.AnimeEntity
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class FavouritesViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = AnimeRepository(application)

    private val _favourites = MutableStateFlow<List<AnimeEntity>>(emptyList())
    val favourites: StateFlow<List<AnimeEntity>> = _favourites

    fun loadFavourites() {
        viewModelScope.launch {
            _favourites.value = repository.getFavourites()
        }
    }

    fun removeFavourite(entity: AnimeEntity) {
        viewModelScope.launch {
            repository.removeFromFavourites(entity)
            loadFavourites()
        }
    }
}
