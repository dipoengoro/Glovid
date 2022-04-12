package com.dicoding.myrecyclerview

import java.io.Serializable

data class News(
    var title: String = "",
    var date: String = "",
    var content: String = "",
    var thumbnail: Int = 0
) : Serializable