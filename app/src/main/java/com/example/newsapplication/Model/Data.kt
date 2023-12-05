package com.example.newsapplication.Model

data class Data(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)