package com.dicoding.myrecyclerview.ui

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.dicoding.myrecyclerview.R
import com.dicoding.myrecyclerview.databinding.FragmentHomeBinding
import com.dicoding.myrecyclerview.viewmodel.MainViewModel
import java.time.LocalDateTime

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding

    private lateinit var homeViewModel: MainViewModel

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        homeViewModel = ViewModelProvider(requireActivity())[MainViewModel::class.java]
        val current = LocalDateTime.now()
        binding.apply {
            // Set click untuk Indo card
            indoNewsCard.setOnClickListener {
                requireActivity().supportFragmentManager.beginTransaction()
                    .replace(R.id.fragmentContainerView, ProvincesFragment())
                    .addToBackStack(HomeFragment::class.java.simpleName)
                    .commit()
            }
            // Set click untuk Global card
            globalNewsCard.setOnClickListener {
                requireActivity().supportFragmentManager.beginTransaction()
                    .replace(R.id.fragmentContainerView, CountriesFragment())
                    .addToBackStack(HomeFragment::class.java.simpleName)
                    .commit()
            }
            viewModel = homeViewModel
            lifecycleOwner = viewLifecycleOwner
            val greetingText = when (current.hour) {
                in 0..5 -> "Malam"
                in 6..12 -> "Pagi"
                in 12..14 -> "Siang"
                in 15..18 -> "Sore"
                else -> "Malam"
            }
            greeting.text = getString(R.string.greeting, greetingText)
        }
        return binding.root
    }


}