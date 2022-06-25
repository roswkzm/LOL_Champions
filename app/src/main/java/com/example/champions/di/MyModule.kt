package com.example.champions.di

import com.example.champions.BuildConfig.BASE_URL
import com.example.champions.BuildConfig.LOL_VERSION
import com.example.champions.network.ChampionService
import com.example.champions.repository.DetailRepository
import com.example.champions.repository.MainRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MyModule {

    @Provides
    @Singleton
    fun provideRetrofit() : Retrofit{
        return Retrofit.Builder()
            .baseUrl("${BASE_URL}/${LOL_VERSION}/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideChampionService(retrofit: Retrofit) : ChampionService{
        return retrofit.create(ChampionService::class.java)
    }

    @Provides
    @Singleton
    fun provideMainRepository(championService: ChampionService) : MainRepository{
        return MainRepository(championService)
    }

    @Provides
    @Singleton
    fun provideDetailRepository(championService: ChampionService) : DetailRepository{
        return DetailRepository(championService)
    }
}