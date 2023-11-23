package com.example.newsapplicationchallenge.domain.repository

import com.example.newsapplicationchallenge.data.network.common.Resource
import com.example.newsapplicationchallenge.domain.model.NewsResponse

interface NewsRepository {
    suspend fun getTopHeadlines(): Resource<NewsResponse>
}