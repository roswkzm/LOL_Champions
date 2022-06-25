package com.example.champions.repository

import com.example.champions.network.ChampionService
import javax.inject.Inject

class DetailRepository @Inject constructor(
    private val championService: ChampionService) {

    suspend fun getChampionDetail(id : String) = championService.getDetailChampion(id)
}