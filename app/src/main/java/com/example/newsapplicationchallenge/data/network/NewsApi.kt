package com.example.newsapplicationchallenge.data.network

import com.example.newsapplicationchallenge.domain.model.NewsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {
    companion object {
        const val BASE_URL = "https://newsapi.org/v2/"
        const val API_KEY = "55fc3c76f3d049d9b3c5a6714a43f12a"
    }

    @GET("top-headlines")
    suspend fun getTopNews(
        @Query("country") country: String = "us",
        @Query("apiKey") apiKey: String = API_KEY
    ): NewsResponse
}