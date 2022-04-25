package com.dicoding.myrecyclerview.util

import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.myrecyclerview.adapters.ListCountriesAdapter
import com.dicoding.myrecyclerview.adapters.ListProvinceAdapter
import com.dicoding.myrecyclerview.model.global.Country
import com.dicoding.myrecyclerview.model.provinsi.Provinsi
import java.text.NumberFormat

/*
 * Ini adalah file untuk membuat custom attribute yang dapat digunakan pada layout xml
 */

// Ini akan membuat attribute listData yang berfungsi untuk supply list ke adapter
@BindingAdapter("listData")
fun RecyclerView.bindRecyclerView(data: List<Provinsi>?) =
    data?.run {
        val provinceAdapter = ListProvinceAdapter()
        adapter = provinceAdapter
        provinceAdapter.addItems(this.sortedBy { it.name })
        layoutManager = LinearLayoutManager(context)
    }

// Ini akan membuat attribute numberText yang berfungsi untuk menambahkan titik setiap 3 angka
@BindingAdapter("numberText")
fun bindNumberText(textView: TextView, number: Long) =
    number.let {
        textView.text = NumberFormat.getNumberInstance()
            .format(it)
            .replace(",", ".")
    }

// Ini sama aja tapi untuk country
@BindingAdapter("listCountries")
fun RecyclerView.bindCountries(data: List<Country>?) =
    data?.run {
        val countriesAdapter = ListCountriesAdapter()
        adapter = countriesAdapter
        countriesAdapter.addCountries(this.sortedBy { it.name })
        layoutManager = LinearLayoutManager(context)
    }