package com.shrey.newsbee.network

import com.shrey.newsbee.model.News
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

// Base URL for the News API
const val BASE_URL = "https://newsapi.org/"

// API Key for accessing the News API. You can get it from "https://newsapi.org" after signing in.
const val API_KEY = ""

// Interface defining the endpoints for the News API
interface NewsInterface {

    // Endpoint to get top headlines
    @GET("v2/top-headlines?apiKey=$API_KEY")
    fun getHeadlines(
        @Query("country") country: String, // Query parameter for country
        @Query("page") page: Int // Query parameter for page number
    ): retrofit2.Call<News> // Call object for handling asynchronous requests

}

// Singleton object for handling network requests
object NewsService {
    val newsInterface: NewsInterface // Interface implementation for accessing News API

    init {
        // Create Retrofit instance
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL) // Set base URL
            .addConverterFactory(GsonConverterFactory.create()) // Add Gson converter factory for JSON parsing
            .build()
        // Create instance of NewsInterface
        newsInterface = retrofit.create(NewsInterface::class.java)
    }
}
