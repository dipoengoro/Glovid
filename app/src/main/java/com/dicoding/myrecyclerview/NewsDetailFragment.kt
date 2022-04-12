package com.dicoding.myrecyclerview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.dicoding.myrecyclerview.databinding.NewsDetailFragmentBinding

class NewsDetailFragment(private val news: News) : Fragment() {
    private lateinit var binding: NewsDetailFragmentBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = NewsDetailFragmentBinding.inflate(inflater, container, false)

        binding.apply {
            newsDetailImage.setImageResource(news.thumbnail)
            newsDetailTitle.text = news.title
            newsDetailDate.text = news.date
            newsContent.text = news.content
        }

        return binding.root
    }
}