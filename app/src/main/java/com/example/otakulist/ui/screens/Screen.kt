package com.example.otakulist.ui.screens

sealed class Screen(val route: String) {
    object AnimeList : Screen("anime_list")
    object AnimeDetails : Screen("anime_details/{animeId}") {
        fun passId(id: Int) = "anime_details/$id"
    }
    object Favourites : Screen("favourites")
    object Settings : Screen("settings")
}
