package com.dicoding.myrecyclerview

import java.lang.reflect.Array.get

object ProvinceData {
    private val provinceNames = arrayOf(
        "Data Provinsi di Indonesia",
        "Provinsi Aceh",
        "Provinsi Bali",
        "Provinsi Bangka Belitung",
        "Provinsi Banten",
        "Provinsi Bengkulu"
    )

    private val proviceKasus = arrayOf(
        "",
        "43,459",
        "43,459",
        "43,459",
        "43,459",
        "43,459"
    )

    private val provinceDirawat = arrayOf(
        "",
        "1,325",
        "1,325",
        "1,325",
        "1,325",
        "1,325"
    )

    private val provinceSembuh = arrayOf(
        "",
        "39,993",
        "39,993",
        "39,993",
        "39,993",
        "39,993",
    )

    private val provinceImg = intArrayOf(
        0,
        R.drawable.prov_aceh,
        R.drawable.prov_bali,
        R.drawable.prov_bangka_belitung,
        R.drawable.prov_banten,
        R.drawable.prov_bengkulu
    )

    val listData: ArrayList<Province>
        get() {
            val list = arrayListOf<Province>()
            for (position in provinceNames.indices) {
                val prov = Province()
                prov.name = provinceNames[position]
                prov.kasus = proviceKasus[position]
                prov.dirawat = provinceDirawat[position]
                prov.sembuh = provinceSembuh[position]
                prov.photo = provinceImg[position]
                list.add(prov)
            }
            return list
        }
}