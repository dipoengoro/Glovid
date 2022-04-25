package com.dicoding.myrecyclerview.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.myrecyclerview.R
import com.dicoding.myrecyclerview.databinding.ItemCountryBinding
import com.dicoding.myrecyclerview.model.global.Country
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

private const val ITEM_VIEW_TYPE_HEADER = 0
private const val ITEM_VIEW_TYPE_ITEM = 1

/*
 * Ini adalah class ListCountriesAdapter yang mengimplementasikan ListAdapter<Country, RecyclerView.ViewHolder>
 */
class ListCountriesAdapter :
    ListAdapter<DataItemCountries, RecyclerView.ViewHolder>(CountriesDiffCallback) {

    private val adapterScope = CoroutineScope(Dispatchers.Default)
    // Ini adalah fungsi untuk menggabungkan list yang didapat dari api dan headernya
    fun addCountries(list: List<Country>?) {
        adapterScope.launch {
            val items = when (list) {
                null -> listOf(DataItemCountries.CountryHeader)
                else -> listOf(DataItemCountries.CountryHeader) + list.map { DataItemCountries.CountryItem(it) }
            }
            withContext(Dispatchers.Main) {
                submitList(items)
            }
        }
    }
    // Method ini digunakan untuk memisahkan antara header dan item
    override fun getItemViewType(position: Int): Int = when (getItem(position)) {
        is DataItemCountries.CountryHeader -> ITEM_VIEW_TYPE_HEADER
        is DataItemCountries.CountryItem -> ITEM_VIEW_TYPE_ITEM
    }

    // Ini adalah method yang digunakan untuk mengatur viewholder untuk item dan header
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        when (viewType) {
            ITEM_VIEW_TYPE_HEADER -> HeaderCountryViewHolder.from(parent)
            ITEM_VIEW_TYPE_ITEM -> CountryViewHolder.from(parent)
            else -> throw ClassCastException("Unknown viewType $viewType")
        }

    // Ini adalah method yang digunakan untuk mengirimkan data ke viewholder untuk ditampilkan
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) =
        when (holder) {
            is CountryViewHolder -> {
                holder.bind((getItem(position) as DataItemCountries.CountryItem).country)
            }
            is HeaderCountryViewHolder -> {}
            else -> throw ClassCastException("Unknown view holder")
        }

    // Ini adalah object yang digunakan untuk efisiensi penggunaan memory
    companion object CountriesDiffCallback : DiffUtil.ItemCallback<DataItemCountries>() {
        override fun areItemsTheSame(
            oldItem: DataItemCountries,
            newItem: DataItemCountries
        ): Boolean = oldItem.id == newItem.id

        override fun areContentsTheSame(
            oldItem: DataItemCountries,
            newItem: DataItemCountries
        ): Boolean = oldItem == newItem
    }

    // Ini adalah class yang digunakan untuk menampilkan item country
    class CountryViewHolder(private val binding: ItemCountryBinding) :
        RecyclerView.ViewHolder(binding.root) {
        // Ini adalah function untuk mengirimkan data ke item_country.xml
        fun bind(item: Country) {
            binding.apply {
                country = item
                executePendingBindings()
            }
        }

        companion object {
            fun from(parent: ViewGroup): CountryViewHolder =
                CountryViewHolder(
                    ItemCountryBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
        }
    }

    // Ini adalah class yang digunakan untuk menampilkan header
    class HeaderCountryViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        companion object {
            fun from(parent: ViewGroup) : HeaderCountryViewHolder =
                HeaderCountryViewHolder(
                    LayoutInflater.from(parent.context).inflate(
                        R.layout.countries_header,
                        parent,
                        false
                    )
                )
        }
    }
}

// Ini adalah class yang digunakan untuk melabeli data yang akan ditampilkan
sealed class DataItemCountries {
    data class CountryItem(val country: Country) : DataItemCountries() {
        override val id: Long
            get() = Long.MIN_VALUE
    }

    object CountryHeader : DataItemCountries() {
        override val id: Long
            get() = Long.MAX_VALUE
    }

    abstract val id: Long
}