package com.example.otakulist.model

data class AnimeResponse(
    val data: List<AnimeDto>
)

data class AnimeDto(
    val mal_id: Int,
    val title: String,
    val images: ImagesDto,
    val score: Double?,
    val synopsis: String?
)

data class ImagesDto(
    val jpg: ImageTypeDto
)

data class ImageTypeDto(
    val image_url: String
)

fun AnimeDto.toAnime(): Anime {
    return Anime(
        id = mal_id,
        title = title,
        imageUrl = images?.jpg?.image_url,
        score = score,
        synopsis = synopsis
    )
}