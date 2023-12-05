package com.example.newsapplication.Adapter
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapplication.Model.Article
import com.example.newsapplication.R
import com.squareup.picasso.Picasso

class NewsAdapter(private val context: Context, private val articles: List<Article>) :
    RecyclerView.Adapter<NewsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_news, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val article = articles[position]

        holder.tvTitle.text = article.title
        holder.tvDescription.text = article.description

        Picasso.get().load(article.urlToImage).into(holder.ivImage)
    }

    override fun getItemCount(): Int {
        return articles.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvTitle: TextView = itemView.findViewById(R.id.titleTextView)
        val tvDescription: TextView = itemView.findViewById(R.id.descriptionTextView)
        val ivImage: ImageView = itemView.findViewById(R.id.imageView)
    }
}
