package com.dicoding.myrecyclerview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.myrecyclerview.databinding.FragmentProvincesBinding

class ProvincesFragment : Fragment() {
    private lateinit var binding: FragmentProvincesBinding
    private lateinit var listAdapter: ListProvinceAdapter
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProvincesBinding.inflate(inflater, container, false)
        listAdapter = ListProvinceAdapter()
        binding.recyclerViewProvinces.apply {
            adapter = listAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
        listAdapter.setList(ProvinceData.listData)
        return binding.root
    }
}