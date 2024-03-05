package com.shrey.newsbee.model

// Data class representing an article retrieved from the News API
data class Article(
    val author: String, // Author of the article
    val title: String, // Title of the article
    val description: String, // Description of the article
    val url: String, // URL of the article
    val urlToImage: String // URL of the article's image
)
