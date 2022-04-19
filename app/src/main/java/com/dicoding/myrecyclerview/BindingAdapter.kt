package com.dicoding.myrecyclerview

import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.myrecyclerview.provinsi.Provinsi
import java.text.NumberFormat

@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<Provinsi>) =
    data.let {
        (recyclerView.adapter as ListProvinceAdapter).submitList(it)
    }

@BindingAdapter("numberText")
fun bindNumberText(textView: TextView, number: Long) =
    number.let {
        textView.text = NumberFormat.getNumberInstance()
            .format(it)
            .replace(",", ".")
    }