package com.dicoding.myrecyclerview

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.myrecyclerview.provinsi.Provinsi

@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<Provinsi>) =
    data.let {
        (recyclerView.adapter as ListProvinceAdapter).submitList(it)
    }