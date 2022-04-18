package com.dicoding.myrecyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.myrecyclerview.databinding.ItemRowProvBinding
import com.dicoding.myrecyclerview.provinsi.Provinsi

class ListProvinceAdapter : ListAdapter<
        Provinsi,
        ListProvinceAdapter.ViewHolder
        >(DiffCallback) {
    inner class ViewHolder(private val binding: ItemRowProvBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(provinsiItem: Provinsi) {
            binding.apply {
                provinsi = provinsiItem
                executePendingBindings()
            }
        }
    }

    companion object DiffCallback : DiffUtil.ItemCallback<Provinsi>() {
        override fun areItemsTheSame(oldItem: Provinsi, newItem: Provinsi): Boolean =
            oldItem === newItem

        override fun areContentsTheSame(oldItem: Provinsi, newItem: Provinsi): Boolean =
            oldItem.name == newItem.name
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(ItemRowProvBinding.inflate(LayoutInflater.from(parent.context)))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(getItem(position))
}

