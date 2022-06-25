package com.example.champions.model

data class ChampionResponse<T> (
    val data : Map<String, T>
        ){
    fun toList(): List<T> = data.values.toList()
}