package com.example.otakulist.ui.screens.anime_list

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Settings
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.layout.ContentScale
import coil.compose.AsyncImage
import com.example.otakulist.model.Anime
import androidx.compose.foundation.clickable

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AnimeListScreen(
    viewModel: AnimeListViewModel,
    onAnimeClick: (Int) -> Unit,
    onFavouritesClick: () -> Unit,
    onSettingsClick: () -> Unit
) {
    val animeList by viewModel.animeList.collectAsState()

    LaunchedEffect(Unit) { viewModel.loadAnime() }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("OtakuList", style = MaterialTheme.typography.titleLarge) },
                actions = {
                    IconButton(onClick = onFavouritesClick) {
                        Icon(Icons.Default.Favorite, contentDescription = null)
                    }
                    IconButton(onClick = onSettingsClick) {
                        Icon(Icons.Default.Settings, contentDescription = null)
                    }
                }
            )
        }
    ) { padding ->

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier
                .padding(padding)
                .padding(12.dp),
            contentPadding = PaddingValues(4.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {

            items(animeList) { anime ->
                AnimeCard(anime = anime, onClick = { onAnimeClick(anime.id) })
            }
        }
    }
}

@Composable
fun AnimeCard(anime: Anime, onClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(MaterialTheme.shapes.medium)
            .padding(bottom = 4.dp)
            .clickable { onClick() }
    ) {

        AsyncImage(
            model = anime.imageUrl,
            contentDescription = anime.title,
            modifier = Modifier
                .fillMaxWidth()
                .height(220.dp)
                .clip(MaterialTheme.shapes.medium),
            contentScale = ContentScale.Crop
        )

        Text(
            text = anime.title,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(top = 6.dp, start = 4.dp, end = 4.dp)
        )
    }
}
