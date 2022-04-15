package ru.twotwobyte.news

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import ru.twotwobyte.news.pojo.Article

class NewsAdapter : RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {

    var listOfNews: List<Article> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    var onClickListenerNewsAdapter: OnClickListenerNews? = null

    inner class NewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var title: TextView = itemView.findViewById(R.id.titleTextView)
        var description: TextView = itemView.findViewById(R.id.descriptionTextView)
        var image: ImageView = itemView.findViewById(R.id.imageView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val viewItem =
            LayoutInflater.from(parent.context).inflate(R.layout.item_of_news, parent, false)
        return NewsViewHolder(viewItem)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val item = listOfNews[position]
        with(holder) {
            title.text = item.title
            description.text = item.description
            if (item.urlToImage != null || item.urlToImage == "") {
                image.visibility = View.VISIBLE
                Picasso.get()
                    .load(item.urlToImage)
                    .into(image)
            } else image.visibility = View.GONE
        }
        holder.itemView.setOnClickListener { onClickListenerNewsAdapter?.onClick(item.url.toString()) }
    }

    override fun getItemCount() = listOfNews.size

    fun interface OnClickListenerNews {
        fun onClick(url: String)
    }
}