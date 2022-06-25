package com.example.champions.network

import com.example.champions.model.Champion
import com.example.champions.model.ChampionDetail
import com.example.champions.model.ChampionResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ChampionService {

    @GET("data/en_US/champion.json")
    suspend fun getAllChampions() : Response<ChampionResponse<Champion>>

    @GET("data/en_US/champion/{id}.json")
    suspend fun getDetailChampion(@Path("id") id : String) : Response<ChampionResponse<ChampionDetail>>
}