package com.dicoding.myrecyclerview

import android.util.Log
import androidx.lifecycle.*
import com.dicoding.myrecyclerview.indonesia.Indonesia
import com.dicoding.myrecyclerview.network.Api
import kotlinx.coroutines.launch
import java.text.NumberFormat

class HomeViewModel : ViewModel() {

    private val _indonesiaTotal = MutableLiveData<Indonesia>()

    val jumlahPositif = Transformations.map(_indonesiaTotal) {
        NumberFormat.getNumberInstance()
            .format(it.positif)
            .replace(",", ".")
    }

    val jumlahSembuh = Transformations.map(_indonesiaTotal) {
        NumberFormat.getNumberInstance()
            .format(it.sembuh)
            .replace(",", ".")
    }

    val jumlahMeninggal = Transformations.map(_indonesiaTotal) {
        NumberFormat.getNumberInstance()
            .format(it.meninggal)
            .replace(",", ".")
    }

    init {
        getUpdate()
    }

    private fun getUpdate() = viewModelScope.launch {
        try {
            Api.retrofitService.getUpdateData().let {
                _indonesiaTotal.value = it
                Log.i("HomeViewModel", "getUpdate: ${_indonesiaTotal.value}")
            }
        } catch (e: Exception) {
            Log.i("HomeViewModel", "getUpdate: ${e.message}")
        }
    }
}