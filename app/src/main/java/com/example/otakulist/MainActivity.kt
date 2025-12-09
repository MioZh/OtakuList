package com.example.otakulist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.*
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.*
import androidx.navigation.navArgument
import com.example.otakulist.ui.screens.Screen
import com.example.otakulist.ui.screens.anime_list.AnimeListScreen
import com.example.otakulist.ui.screens.anime_details.AnimeDetailsScreen
import com.example.otakulist.ui.screens.favourites.FavouritesScreen
import com.example.otakulist.ui.screens.settings.SettingsScreen
import com.example.otakulist.ui.screens.anime_list.AnimeListViewModel
import com.example.otakulist.ui.screens.anime_details.AnimeDetailsViewModel
import com.example.otakulist.ui.screens.favourites.FavouritesViewModel
import com.example.otakulist.ui.theme.ThemeViewModel

class MainActivity : ComponentActivity() {

    private val animeListViewModel: AnimeListViewModel by lazy {
        ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        )[AnimeListViewModel::class.java]
    }

    private val animeDetailsViewModel: AnimeDetailsViewModel by lazy {
        ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        )[AnimeDetailsViewModel::class.java]
    }

    private val favouritesViewModel: FavouritesViewModel by lazy {
        ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        )[FavouritesViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {

            val themeViewModel: ThemeViewModel = viewModel()
            val isDarkMode by themeViewModel.darkMode.collectAsState(initial = false)

            MaterialTheme(
                colorScheme = if (isDarkMode) darkColorScheme() else lightColorScheme()
            ) {

                val navController = rememberNavController()

                NavHost(
                    navController = navController,
                    startDestination = Screen.AnimeList.route
                ) {

                    composable(Screen.AnimeList.route) {
                        AnimeListScreen(
                            viewModel = animeListViewModel,
                            onAnimeClick = { id ->
                                navController.navigate(Screen.AnimeDetails.passId(id))
                            },
                            onFavouritesClick = {
                                navController.navigate(Screen.Favourites.route)
                            },
                            onSettingsClick = {
                                navController.navigate(Screen.Settings.route)
                            }
                        )
                    }

                    composable(Screen.Favourites.route) {
                        FavouritesScreen(
                            viewModel = favouritesViewModel,
                            onNavigateToDetails = { id ->
                                navController.navigate(Screen.AnimeDetails.passId(id))
                            },
                            onBack = { navController.popBackStack() }
                        )
                    }

                    composable(Screen.Settings.route) {
                        SettingsScreen(
                            onBack = { navController.popBackStack() },
                            themeViewModel = themeViewModel
                        )
                    }

                    composable(
                        route = Screen.AnimeDetails.route,
                        arguments = listOf(
                            navArgument("animeId") { type = NavType.IntType }
                        )
                    ) { backStackEntry ->

                        val id = backStackEntry.arguments?.getInt("animeId") ?: 0

                        AnimeDetailsScreen(
                            viewModel = animeDetailsViewModel,
                            animeId = id,
                            onBack = { navController.popBackStack() }
                        )
                    }
                }
            }
        }
    }
}
