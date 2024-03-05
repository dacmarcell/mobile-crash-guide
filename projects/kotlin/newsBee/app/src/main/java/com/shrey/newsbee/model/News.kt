package com.shrey.newsbee.model

import com.shrey.newsbee.model.Article

// Data class representing the response from the News API
data class News(
    val totalResults: Int, // Total number of results returned by the API
    val articles: List<Article> // List of articles retrieved from the API
)
