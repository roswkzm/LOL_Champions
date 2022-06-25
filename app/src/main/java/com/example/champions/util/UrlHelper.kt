package com.example.champions.util

import android.util.Log
import com.example.champions.BuildConfig

fun getSplashImageUri(championId : String?) : String{
    return "${BuildConfig.BASE_URL}/img/champion/splash/${championId}_0.jpg"
}

fun getSkillImageUrl(skillId: String?) : String{
    return "${BuildConfig.BASE_URL}/${BuildConfig.LOL_VERSION}/img/spell/${skillId}.png"
}

fun getPassiveImageUrl(passiveId: String?) : String{
    return "${BuildConfig.BASE_URL}/${BuildConfig.LOL_VERSION}/img/passive/${passiveId}"
}

fun getSkinImageUrl(championId: String?, skinNum : Int) : String{
    //https://ddragon.leagueoflegends.com/cdn/img/champion/loading/Darius_1.jpg
    return "${BuildConfig.BASE_URL}/img/champion/loading/${championId}_${skinNum}.jpg"
}