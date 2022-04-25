package com.dicoding.myrecyclerview.model.global

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize

// Ini adalah class untuk parsing data dari JSON
@Parcelize
data class GlobalResponse(
    @Json(name = "ID")
    val id: String,
    @Json(name = "Message")
    val message: String,
    @Json(name = "Global")
    val global: Global,
    @Json(name = "Countries")
    val countries: List<Country>,
    @Json(name = "Date")
    val date: String
) : Parcelable

@Parcelize
data class Global(
    @Json(name = "TotalConfirmed")
    val confirmed: Long,
    @Json(name = "TotalDeaths")
    val deaths: Long,
    @Json(name = "TotalRecovered")
    val recovered: Long
) : Parcelable

@Parcelize
data class Country(
    @Json(name = "Country")
    val name: String,
    @Json(name = "TotalConfirmed")
    val confirmed: Long,
    @Json(name = "TotalDeaths")
    val deaths: Long,
    @Json(name = "TotalRecovered")
    val recovered: Long
) : Parcelable