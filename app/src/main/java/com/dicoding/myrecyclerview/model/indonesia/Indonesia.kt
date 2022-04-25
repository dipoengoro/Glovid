package com.dicoding.myrecyclerview.model.indonesia

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize

// Ini adalah class untuk data Indonesia
@Parcelize
data class Indonesia(
    val positif: Long,
    val dirawat: Long,
    val sembuh: Long,
    val meninggal: Long
) : Parcelable