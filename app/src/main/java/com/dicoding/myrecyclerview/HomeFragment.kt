package com.dicoding.myrecyclerview

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import com.dicoding.myrecyclerview.databinding.FragmentHomeBinding
import java.time.LocalDateTime

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        val current = LocalDateTime.now()
        binding.apply {
            indoNewsCard.setOnClickListener {
                requireActivity().supportFragmentManager.beginTransaction()
                    .replace(R.id.fragmentContainerView, ProvincesFragment())
                    .addToBackStack(HomeFragment::class.java.simpleName)
                    .commit()
            }
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