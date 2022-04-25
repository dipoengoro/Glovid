package com.dicoding.myrecyclerview.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.myrecyclerview.model.News
import com.dicoding.myrecyclerview.databinding.ItemNewsBinding

class NewsAdapter(
    private val onItemClicked: (News) -> Unit
) : RecyclerView.Adapter<NewsAdapter.ViewHolder>() {

    private val newsList = ArrayList<News>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(ItemNewsBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(newsList[position]) { onItemClicked(it) }

    override fun getItemCount(): Int = newsList.size

    fun setList(news: List<News>) {
        newsList.clear()
        newsList.addAll(news)
    }

    inner class ViewHolder(private val binding: ItemNewsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(
            news: News,
            onItemClicked: (News) -> Unit
        ) {
            binding.apply {
                newsDate.text = news.date
                newsTitle.text = news.title
                newsImage.setImageResource(news.thumbnail)
                buttonReadMore.setOnClickListener {
                    onItemClicked(news)
                }
            }
        }

    }
}