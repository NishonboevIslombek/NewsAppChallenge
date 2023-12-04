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
    suspend fun getTopHeadlines(
        @Query("country") country: String = "us",
        @Query("pageSize") pageSize: Int = 10,
        @Query("apiKey") apiKey: String = API_KEY
    ): NewsResponse

    //everything endpoint sortBy publishedAt query which is the newest articles come first
    @GET("everything")
    suspend fun getRecentNews(
        @Query("domains") domain: String = "bbc.co.uk",
        @Query("apiKey") apiKey: String = API_KEY
    ): NewsResponse

    @GET("everything")
    suspend fun getPopularNews(
        @Query("domains") domain: String = "bbc.co.uk",
        @Query("sortBy") sortBy: String = "popularity",
        @Query("apiKey") apiKey: String = API_KEY
    ): NewsResponse
}