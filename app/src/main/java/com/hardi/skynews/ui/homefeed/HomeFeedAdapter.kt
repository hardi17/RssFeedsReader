package com.hardi.skynews.ui.homefeed

import android.annotation.SuppressLint
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.browser.customtabs.CustomTabsIntent
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.hardi.skynews.R
import com.hardi.skynews.data.model.FeedItems
import com.hardi.skynews.databinding.NewsItemLayoutBinding

class HomeFeedAdapter(
    private val newsList: ArrayList<FeedItems>
) : RecyclerView.Adapter<HomeFeedAdapter.DataViewHolder>() {

    class DataViewHolder(private val binding: NewsItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(items: FeedItems) {
            //Display news article title
            binding.newsTitle.text = items.title

            //Display news article description
            //if it's null/failing fetching from XML response then default msg shown
            val descValue = items.description ?: itemView.context.getString(R.string.no_description)
            binding.newsDescription.text = descValue

            //Display news article image
            Glide.with(binding.newsImage.context)
                .load(items.thumbnail?.url)
                .error(R.drawable.ic_launcher_background)
                .into(binding.newsImage)

            //Open news article in a browser
            itemView.setOnClickListener {
                val builder = CustomTabsIntent.Builder()
                val customTabsIntent = builder.build()
                customTabsIntent.launchUrl(it.context, Uri.parse(items.link))
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ) = DataViewHolder(
        NewsItemLayoutBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )


    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        return holder.bind(newsList[position])
    }

    override fun getItemCount(): Int {
        return newsList.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun addData(list: List<FeedItems>) {
        newsList.clear()
        newsList.addAll(list)
        this.notifyDataSetChanged()
    }
}