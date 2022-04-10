package com.dicoding.myrecyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dicoding.myrecyclerview.databinding.ItemRowProvBinding

class ListProvinceAdapter : RecyclerView.Adapter<ListProvinceAdapter.ListViewHolder>() {

    private val listProvince = ArrayList<Province>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder =
        ListViewHolder(
            ItemRowProvBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) =
        holder.bind(listProvince[position])

    override fun getItemCount(): Int = listProvince.size

    fun setList(provinces: List<Province>) {
        listProvince.clear()
        listProvince.addAll(provinces)
    }

    inner class ListViewHolder(private val binding: ItemRowProvBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(province: Province) {
            binding.apply {
                provinceNewsTitle.text = province.name
                Glide.with(binding.root.context)
                    .load(province.photo)
                    .into(provinceNewsImage)
                provinceNumberCasePositive.text = province.kasus
                provinceNumberCaseRecover.text = province.sembuh
                provinceNumberCaseDeath.text = province.dirawat
            }
        }
    }
}
