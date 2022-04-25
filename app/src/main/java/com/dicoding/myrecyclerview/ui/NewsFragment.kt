package com.dicoding.myrecyclerview.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.dicoding.myrecyclerview.R
import com.dicoding.myrecyclerview.adapters.NewsAdapter
import com.dicoding.myrecyclerview.databinding.FragmentNewsBinding


class NewsFragment : Fragment() {
    private lateinit var binding: FragmentNewsBinding
    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNewsBinding.inflate(inflater, container, false)
        binding.apply {
            webview.apply {
                loadUrl(getString(R.string.default_url))
                settings.javaScriptEnabled = true
            }

            return root
        }
    }
}