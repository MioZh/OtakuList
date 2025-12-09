package com.example.otakulist.ui.theme

import android.app.Application
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

private val Application.dataStore by preferencesDataStore(name = "settings")

class ThemeViewModel(application: Application) : AndroidViewModel(application) {

    private val DARK_MODE_KEY = booleanPreferencesKey("dark_mode")

    val darkMode: Flow<Boolean> = application.dataStore.data.map { prefs ->
        prefs[DARK_MODE_KEY] ?: false
    }

    // сохраняем новую тему в DataStore
    fun setDarkMode(enabled: Boolean) {
        viewModelScope.launch {
            getApplication<Application>().dataStore.edit { prefs ->
                prefs[DARK_MODE_KEY] = enabled
            }
        }
    }
}
