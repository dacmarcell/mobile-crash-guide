package com.shrey.newsbee.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.shrey.newsbee.R
import com.shrey.newsbee.activity.WebActivity
import com.shrey.newsbee.model.Article

// Adapter class for displaying news articles in a RecyclerView
class NewsAdapter(val context: Context, val article: List<Article>) :
    RecyclerView.Adapter<NewsAdapter.ArticleNewsViewHolder>() {

    // Create a new ViewHolder instance
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleNewsViewHolder {
        // Inflate the layout for each item in the RecyclerView
        val view = LayoutInflater.from(context).inflate(R.layout.item_view, parent, false)
        return ArticleNewsViewHolder(view)
    }

    // Return the total number of items in the dataset
    override fun getItemCount(): Int {
        return article.size
    }

    // Bind data to the ViewHolder
    override fun onBindViewHolder(holder: ArticleNewsViewHolder, position: Int) {
        // Retrieve the article at the specified position
        val art = article[position]
        // Bind article data to the ViewHolder
        holder.newsTitle.text = art.title
        holder.newsDescription.text = art.description
        // Load image using Glide library
        Glide.with(context).load(art.urlToImage).into(holder.newsImage)
        // Set click listener for the item
        holder.itemView.setOnClickListener {
            // Display a toast message when the item is clicked
            Toast.makeText(context, "Clicked on ${holder.newsTitle.text}", Toast.LENGTH_SHORT)
                .show()
            // Open the web activity to display the full article
            val intent = Intent(context, WebActivity::class.java)
            intent.putExtra("URL", art.url)
            context.startActivity(intent)

        }
    }

    // ViewHolder class to hold references to views
    class ArticleNewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val newsImage =
            itemView.findViewById<ImageView>(R.id.imgNews) // Image view for article image
        val newsTitle =
            itemView.findViewById<TextView>(R.id.txtNewsTitle) // Text view for article title
        val newsDescription =
            itemView.findViewById<TextView>(R.id.txtNewsDescription) // Text view for article description
    }
}
