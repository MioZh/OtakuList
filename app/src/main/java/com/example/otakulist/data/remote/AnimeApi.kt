package com.example.otakulist.data.remote

import com.example.otakulist.model.AnimeResponse
import retrofit2.http.GET
import com.example.otakulist.model.AnimeDetailsResponse
import retrofit2.http.Path

interface AnimeApi {

    @GET("v4/anime")
    suspend fun getAnimeList(): AnimeResponse

    @GET("v4/anime/{id}")
    suspend fun getAnimeDetails(@Path("id") id: Int): AnimeDetailsResponse
}
