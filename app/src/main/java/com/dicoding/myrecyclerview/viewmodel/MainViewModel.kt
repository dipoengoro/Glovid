package com.dicoding.myrecyclerview.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dicoding.myrecyclerview.model.global.GlobalResponse
import com.dicoding.myrecyclerview.model.indonesia.Indonesia
import com.dicoding.myrecyclerview.model.provinsi.Provinsi
import com.dicoding.myrecyclerview.network.Api
import com.dicoding.myrecyclerview.network.ApiGlobal
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    // variable untuk menampung data indonesia
    private val _indonesiaTotal = MutableLiveData<Indonesia>()
    val indonesiaTotal: LiveData<Indonesia>
        get() = _indonesiaTotal

    // variable untuk menampung data list provinsi
    private val _provinsiItems = MutableLiveData<List<Provinsi>>()
    val provinsiItems: LiveData<List<Provinsi>>
        get() = _provinsiItems

    // variable untuk menampung data global
    private val _globalData = MutableLiveData<GlobalResponse?>()
    val globalData: LiveData<GlobalResponse?>
        get() = _globalData

    init {
        getUpdate()
        getProvinces()
        getGlobalData()
    }

    // function untuk mengambil data indonesia
    private fun getUpdate() = viewModelScope.launch {
        try {
            Api.retrofitService.getUpdateData().let {
                _indonesiaTotal.value = it
            }
        } catch (e: Exception) {
            Log.i("HomeViewModel", "getUpdate: ${e.message}")
        }
    }
    // function untuk mengambil list provinsi
    private fun getProvinces() = viewModelScope.launch {
        try {
            Api.retrofitService.getProvData().let {
                _provinsiItems.value = it
            }
        } catch (e: Exception) {
            _provinsiItems.value = ArrayList()
        }
    }
    // function untuk mengambil data global
    private fun getGlobalData() = viewModelScope.launch {
        try {
            ApiGlobal.retrofitServiceGlobal.getGlobal().let {
                _globalData.value = it
            }
        } catch (e: Exception) {
            _globalData.value = null
        }
    }

}