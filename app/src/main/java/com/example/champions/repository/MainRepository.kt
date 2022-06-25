package com.example.champions.repository

import com.example.champions.network.ChampionService
import javax.inject.Inject

class MainRepository @Inject constructor(
    private val championService: ChampionService)
{
    suspend fun getAllChampions() = championService.getAllChampions()
}