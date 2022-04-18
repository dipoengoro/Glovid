package com.dicoding.myrecyclerview.indonesia

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize

@Parcelize
data class Indonesia(
    val positif: Long,
    val dirawat: Long,
    val sembuh: Long,
    val meninggal: Long
) : Parcelable