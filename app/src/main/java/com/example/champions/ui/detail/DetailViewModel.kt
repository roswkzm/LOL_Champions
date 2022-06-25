package com.example.champions.ui.detail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.champions.model.ChampionDetail
import com.example.champions.repository.DetailRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val detailRepository: DetailRepository
) : ViewModel(){

    private val _apiResult = MutableLiveData<ChampionDetail>()
    val apiResult : LiveData<ChampionDetail> = _apiResult

    private val _detailActivityException = MutableLiveData<Exception>()
    val detailActivityException : LiveData<Exception> = _detailActivityException

    private val _showSkinDetailDialog = MutableLiveData<Triple<String, Int, String>>()
    val showSkinDetailDialog : LiveData<Triple<String, Int, String>> = _showSkinDetailDialog

    fun getChampionDetail(id : String){
        viewModelScope.launch {
            try {
                val response = detailRepository.getChampionDetail(id)
                if (response.isSuccessful){
                    _apiResult.value = response.body()?.toList()?.get(0)
                }else{
                }
            }catch (e : Exception){
                _detailActivityException.value = e
            }
        }
    }

    fun clickEvent(championId : String, skinNum : Int, skinName : String){
        _showSkinDetailDialog.value = Triple(championId, skinNum, skinName)
    }
}