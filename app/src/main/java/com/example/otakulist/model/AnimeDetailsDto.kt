package com.example.otakulist.model

data class AnimeDetailsResponse(
    val data: AnimeDetailsDto
)

data class AnimeDetailsDto(
    val mal_id: Int,
    val title: String,
    val synopsis: String?,
    val images: ImagesDto?,
    val score: Double?,
    val episodes: Int?,
    val duration: String?
)

fun AnimeDetailsDto.toAnime(): Anime {
    return Anime(
        id = mal_id,
        title = title,
        synopsis = synopsis,
        imageUrl = images?.jpg?.image_url,
        score = score
    )
}
