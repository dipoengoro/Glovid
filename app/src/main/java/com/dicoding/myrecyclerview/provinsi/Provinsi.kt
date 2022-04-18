package com.dicoding.myrecyclerview.provinsi

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize

@Parcelize
data class Provinsi(
    @Json(name = "provinsi")
    val name: String,
    val kasus: Long,
    val dirawat: Long,
    val sembuh: Long,
    val meninggal: Long
) : Parcelable