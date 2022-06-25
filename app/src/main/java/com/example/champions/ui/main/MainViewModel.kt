package com.example.champions.ui.main

import android.accounts.NetworkErrorException
import android.content.Intent
import android.util.Log
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.champions.model.Champion
import com.example.champions.repository.MainRepository
import com.example.champions.ui.detail.DetailActivity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val mainRepository: MainRepository
) : ViewModel(){

    private val _apiResult = MutableLiveData<List<Champion>>()
    val apiResult : LiveData<List<Champion>> = _apiResult

    private val _mainActivityException = MutableLiveData<Exception>()
    val mainActivityException = _mainActivityException

    private val _championIdForIntent = MutableLiveData<String>()
    val championIdForIntent = _championIdForIntent

    fun getAllChampions(){
        viewModelScope.launch {
            try {
                val response = mainRepository.getAllChampions()
                if (response.isSuccessful){
                    response.body()?.let {
                        _apiResult.value = response.body()!!.toList()
                    }
                } else{
                }
            } catch (e : Exception){
                _mainActivityException.postValue(Exception())
            }
        }
    }

    fun clickEvent(championId : String){
        _championIdForIntent.value = championId
    }
}