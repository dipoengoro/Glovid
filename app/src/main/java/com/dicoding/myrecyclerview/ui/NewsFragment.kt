package com.dicoding.myrecyclerview.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.myrecyclerview.*
import com.dicoding.myrecyclerview.adapters.NewsAdapter
import com.dicoding.myrecyclerview.data.NewsData
import com.dicoding.myrecyclerview.databinding.FragmentNewsBinding
import com.dicoding.myrecyclerview.model.News


class NewsFragment : Fragment() {
    private lateinit var binding: FragmentNewsBinding
    private lateinit var listAdapter: NewsAdapter
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNewsBinding.inflate(inflater, container, false)
        listAdapter = NewsAdapter {
            toDetail(it)
        }
        binding.recyclerViewNews.apply {
            adapter = listAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
        listAdapter.setList(NewsData.listData)
        return binding.root
    }

    private fun toDetail(news: News) {
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainerView, NewsDetailFragment(news))
            .commit()
    }
}