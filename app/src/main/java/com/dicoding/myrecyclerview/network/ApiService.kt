package com.dicoding.myrecyclerview.network

import com.dicoding.myrecyclerview.indonesia.Indonesia
import com.dicoding.myrecyclerview.provinsi.Provinsi
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "https://apicovid19indonesia-v2.vercel.app/api/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface ApiService {
    @GET("indonesia")
    suspend fun getUpdateData(): Indonesia

    @GET("indonesia/provinsi")
    suspend fun getProvData(): List<Provinsi>
}

object Api {
    val retrofitService: ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }
}