package com.shrey.newsbee.activity

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.littlemango.stacklayoutmanager.StackLayoutManager
import com.shrey.newsbee.ColorPicker
import com.shrey.newsbee.network.NewsService
import com.shrey.newsbee.R
import com.shrey.newsbee.adapter.NewsAdapter
import com.shrey.newsbee.model.Article
import com.shrey.newsbee.model.News
import retrofit2.Call
import retrofit2.Response

// MainActivity class responsible for displaying news
class MainActivity : AppCompatActivity() {
    lateinit var adapterr: NewsAdapter // Adapter for displaying news articles
    private var arc =
        mutableListOf<Article>() // List to hold articles. which is empty initially without data
    var pageNum = 1 // Page number for pagination
    var Tag = "Discipline" //Tag for logging
    var totalResult = -1 // Total number of results. For now just initialize any number

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize RecyclerView and NewsAdapter
        adapterr = NewsAdapter(this@MainActivity, arc)
        val recycle: RecyclerView = findViewById(R.id.RecycleNewsList)
        recycle.adapter = adapterr

        // Initialize StackLayoutManager for RecyclerView
        val layoutmanager = StackLayoutManager(StackLayoutManager.ScrollOrientation.BOTTOM_TO_TOP)
        layoutmanager.setPagerMode(true)
        layoutmanager.setPagerFlingVelocity(3000)
        layoutmanager.setItemChangedListener(object : StackLayoutManager.ItemChangedListener {
            override fun onItemChanged(position: Int) {
                // Change background color of ConstraintLayout when item changes
                val container: ConstraintLayout = findViewById(R.id.container)
                container.setBackgroundColor(Color.parseColor(ColorPicker.getColor()))
                // Log first visible item position and total count
                Log.d(Tag, "First visible Item - ${layoutmanager.getFirstVisibleItemPosition()}")
                Log.d(Tag, "Total Count - ${layoutmanager.itemCount}")
                // itemcount = 37 and totalResult = 38. So layoutmanager.itemcount + 1
                // Load more data if the user is close to the end of the list
                if (totalResult > layoutmanager.itemCount + 1 && layoutmanager.getFirstVisibleItemPosition() >= layoutmanager.itemCount - 5) {
                    pageNum++
                    getNews()

                }
            }
        })

        // Set layout manager for RecyclerView
        recycle.layoutManager = layoutmanager

        // Call function to fetch news data
        getNews()
    }

    // Function to fetch news data from the News API
    private fun getNews() {
        // Log request sent for page number
        Log.d(Tag, "Request sent for - $pageNum")
        // Create a call to fetch headlines for a specific country (in this case, "in" for India) and page number
        val newsCall = NewsService.newsInterface.getHeadlines("in", pageNum)
        // Asynchronously execute the call
        newsCall.enqueue(object : retrofit2.Callback<News> {
            // Callback invoked when the request fails
            override fun onFailure(call: Call<News>, t: Throwable) {
                // Log error message along with the exception
                Log.d("Yallah", "Error in fetching News", t)
            }

            // Callback invoked when the request is successful and a response is received
            override fun onResponse(call: Call<News>, response: Response<News>) {
                // Extract the news data from the response body
                val news = response.body()
                // Check if the news data is not null
                if (news != null) {
                    // Log the retrieved news data
                    Log.d("Yallah", news.toString())
                    // Update total result count and add fetched articles to the list, then notify adapter
                    totalResult = news.totalResults
                    arc.addAll(news.articles)
                    adapterr.notifyDataSetChanged()
                }
            }
        })
    }
}
