package com.dicoding.myrecyclerview.network

import com.dicoding.myrecyclerview.model.global.GlobalResponse
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

// Ini adalah class yang mengatur retrofit
// Retrofit berfungsi untuk mengatur koneksi ke server
// Moshi berfungsi untuk mengubah data dari server ke model
// KotlinJsonAdapterFactory berfungsi untuk mengubah data dari model ke json
// MoshiConverterFactory berfungsi untuk mengubah data dari json ke model
// ApiServiceGlobal berfungsi untuk mengatur endpoint
// ApiServiceGlobal ini akan diakses di MainViewModel
private const val BASE_URL = "https://api.covid19api.com/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface ApiServiceGlobal {
    @GET("summary")
    suspend fun getGlobal(): GlobalResponse
}

object ApiGlobal {
    val retrofitServiceGlobal: ApiServiceGlobal by lazy {
        retrofit.create(ApiServiceGlobal::class.java)
    }
}