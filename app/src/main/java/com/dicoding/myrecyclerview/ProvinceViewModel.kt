package com.dicoding.myrecyclerview

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dicoding.myrecyclerview.network.Api
import com.dicoding.myrecyclerview.provinsi.Provinsi
import kotlinx.coroutines.launch

class ProvinceViewModel : ViewModel() {
    private val _provinsiItems = MutableLiveData<List<Provinsi>>()
    val provinsiItems: LiveData<List<Provinsi>>
        get() = _provinsiItems

    init {
        getProvinces()
    }

    private fun getProvinces() = viewModelScope.launch {
        try {
            Api.retrofitService.getProvData().let {
                _provinsiItems.value = it
                Log.i("ProvinceViewModel", "getProvinces: $it")
            }
        } catch (e: Exception) {
            _provinsiItems.value = ArrayList()
        }
    }
}