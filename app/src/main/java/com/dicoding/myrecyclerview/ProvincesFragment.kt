package com.dicoding.myrecyclerview

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.myrecyclerview.databinding.FragmentProvincesBinding

class ProvincesFragment : Fragment() {
    private lateinit var binding: FragmentProvincesBinding
    private val provinceViewModel: ProvinceViewModel by lazy {
        ViewModelProvider(this)[ProvinceViewModel::class.java]
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProvincesBinding.inflate(inflater)
        val provincesAdapter = ListProvinceAdapter()
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = provinceViewModel
        binding.recyclerViewProvinces.adapter = provincesAdapter
        provinceViewModel.provinsiItems.observe(viewLifecycleOwner) {
            provincesAdapter.submitList(it)
            Log.i("ProvincesFragment", "onCreateView: ${it.size}")
        }
        return binding.root
    }
}