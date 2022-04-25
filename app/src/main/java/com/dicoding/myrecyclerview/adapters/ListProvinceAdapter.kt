package com.dicoding.myrecyclerview.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.myrecyclerview.R
import com.dicoding.myrecyclerview.databinding.ItemRowProvBinding
import com.dicoding.myrecyclerview.model.provinsi.Provinsi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

private const val ITEM_VIEW_TYPE_HEADER = 0
private const val ITEM_VIEW_TYPE_ITEM = 1

// Kurang lebih sama seperti ListCountriesAdapter
class ListProvinceAdapter :
    ListAdapter<DataItem, RecyclerView.ViewHolder>(DiffCallback) {

    private val adapterScope = CoroutineScope(Dispatchers.Default)
    fun addItems(list: List<Provinsi>?) {
        adapterScope.launch {
            val items = when (list) {
                null -> listOf(DataItem.Header)
                else -> listOf(DataItem.Header) + list.map { DataItem.ProvinsiItem(it) }
            }
            withContext(Dispatchers.Main) {
                submitList(items)
            }
        }
    }

    override fun getItemViewType(position: Int): Int = when (getItem(position)) {
        is DataItem.Header -> ITEM_VIEW_TYPE_HEADER
        is DataItem.ProvinsiItem -> ITEM_VIEW_TYPE_ITEM
    }

    companion object DiffCallback : DiffUtil.ItemCallback<DataItem>() {
        override fun areItemsTheSame(oldItem: DataItem, newItem: DataItem): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: DataItem, newItem: DataItem): Boolean =
            oldItem == newItem
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        when (viewType) {
            ITEM_VIEW_TYPE_HEADER -> HeaderViewHolder.from(parent)
            ITEM_VIEW_TYPE_ITEM -> ViewHolder.from(parent)
            else -> throw ClassCastException("Unknown viewType $viewType")
        }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) =
        when (holder) {
            is ViewHolder -> {
                holder.bind((getItem(position) as DataItem.ProvinsiItem).provinsi)
            }
            is HeaderViewHolder -> {}
            else -> throw ClassCastException("Unknown view holder")
        }

    class ViewHolder(private val binding: ItemRowProvBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(provinsiItem: Provinsi) {
            binding.apply {
                provinsi = provinsiItem
                executePendingBindings()
            }
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder =
                ViewHolder(
                    ItemRowProvBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
        }
    }

    class HeaderViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        companion object {
            fun from(parent: ViewGroup): HeaderViewHolder =
                HeaderViewHolder(
                    LayoutInflater.from(parent.context)
                        .inflate(R.layout.province_header, parent, false)
                )
        }
    }
}

sealed class DataItem {
    data class ProvinsiItem(val provinsi: Provinsi) : DataItem() {
        override val id: Long
            get() = Long.MAX_VALUE
    }

    object Header : DataItem() {
        override val id: Long
            get() = Long.MIN_VALUE
    }

    abstract val id: Long
}

