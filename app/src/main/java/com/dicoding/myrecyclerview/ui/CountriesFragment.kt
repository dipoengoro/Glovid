package com.dicoding.myrecyclerview.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.dicoding.myrecyclerview.databinding.FragmentCountriesBinding
import com.dicoding.myrecyclerview.viewmodel.MainViewModel

class CountriesFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentCountriesBinding.inflate(inflater, container, false)
        val countryViewModel = ViewModelProvider(requireActivity())[MainViewModel::class.java]
        binding.apply {
            lifecycleOwner = this@CountriesFragment
            viewModel = countryViewModel
            return root
        }
    }
}