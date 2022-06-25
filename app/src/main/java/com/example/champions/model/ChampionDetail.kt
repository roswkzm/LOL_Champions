package com.example.champions.model

data class ChampionDetail(
    val allytips: List<String>?,    // 챔피언 팁
    val blurb: String?,             //  챔피언 설명 짧게
    val lore: String?,              // 챔피언 설명 길게
    val enemytips: List<String>?,   // 적에게 설명되는 팁
    val id: String?,                // 챔피언 ID
    val name: String?,              // 챔피언 이름
    val image: Image?,              // 챔피언 이미지
    val skins: List<Skin>?,         // 챔피언 스킨
    val key: String?,               // 챔피언 번호 (고유 ID)
    val partype: String?,
    val passive: Passive?,          // 가렌 패시브
    val spells: List<Spell>?,       // 가렌 스킬 (패시브 제외)
    val tags: List<String>?,        // 챔피언 태그
    val title: String?              // 챔피언 제목
//    val stats: Stats?,              // 기본 스텟
){
    data class Spell(
        val cooldown: List<Float>?,
        val cooldownBurn: String?,
        val cost: List<Int>?,
        val costBurn: String?,
        val costType: String?,
        val description: String?,
        val effect: List<Any>?,
        val effectBurn: List<Any>?,
        val id: String?,
        val image: Image?,
        val maxammo: String?,
        val maxrank: Int?,
        val name: String?,
        val range: List<Int>?,
        val rangeBurn: String?,
        val resource: String?,
        val tooltip: String?,
        val vars: List<Any>?
    )

    data class Skin(
        val id: String?,
        val name: String?,
        val num: Int?
    )

    data class Passive(
        val description: String?,
        val image: Image?,
        val name: String?
    )

//    data class Stats(
//        val armor: Float?,
//        val armorperlevel: Double?,
//        val attackdamage: Float?,
//        val attackdamageperlevel: Double?,
//        val attackrange: Float?,
//        val attackspeed: Double?,
//        val attackspeedperlevel: Double?,
//        val crit: Float?,
//        val critperlevel: Float?,
//        val hp: Float?,
//        val hpperlevel: Float?,
//        val hpregen: Float?,
//        val hpregenperlevel: Double?,
//        val movespeed: Float?,
//        val mp: Float?,
//        val mpperlevel: Float?,
//        val mpregen: Float?,
//        val mpregenperlevel: Float?,
//        val spellblock: Float?,
//        val spellblockperlevel: Double?
//    )
}