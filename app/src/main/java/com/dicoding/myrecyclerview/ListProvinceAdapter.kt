package com.dicoding.myrecyclerview

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.updateLayoutParams
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dicoding.myrecyclerview.databinding.ItemRowProvBinding
import com.google.android.material.color.MaterialColors.getColor

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
                if (province.name == "Data Provinsi di Indonesia") {
                    provinceNewsTitle.updateLayoutParams<ConstraintLayout.LayoutParams> {
                        startToStart = ConstraintLayout.LayoutParams.PARENT_ID
                        endToEnd = ConstraintLayout.LayoutParams.PARENT_ID
                        topToTop = ConstraintLayout.LayoutParams.PARENT_ID
                        bottomToBottom = ConstraintLayout.LayoutParams.PARENT_ID
                    }
                    provinceNewsTitle.setTextColor(Color.BLACK)
                    provinceNewsTitle.textSize = 18f
                    dataLayout.visibility = View.GONE
                    provinceNewsCard.background = null
                    provinceNewsImage.visibility = View.GONE
                    provinceLineDivider.visibility = View.GONE
                    provinceNumberCasePositive.visibility = View.GONE
                    provinceNumberCaseRecover.visibility = View.GONE
                    provinceNumberCaseDeath.visibility = View.GONE
                    provinceTitleCaseDeath.visibility = View.GONE
                    provinceTitleCasePositive.visibility = View.GONE
                    provinceTitleCaseRecover.visibility = View.GONE
                }
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
