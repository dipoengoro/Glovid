package com.dicoding.myrecyclerview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.myrecyclerview.databinding.FragmentNewsBinding


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