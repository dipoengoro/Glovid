package com.dicoding.myrecyclerview.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.dicoding.myrecyclerview.databinding.FragmentProvincesBinding
import com.dicoding.myrecyclerview.viewmodel.MainViewModel

class ProvincesFragment : Fragment() {
    private lateinit var binding: FragmentProvincesBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProvincesBinding.inflate(inflater)
        val provinceViewModel = ViewModelProvider(requireActivity())[MainViewModel::class.java]
        binding.apply {
            lifecycleOwner = this@ProvincesFragment
            viewModel = provinceViewModel
        }

        return binding.root
    }
}