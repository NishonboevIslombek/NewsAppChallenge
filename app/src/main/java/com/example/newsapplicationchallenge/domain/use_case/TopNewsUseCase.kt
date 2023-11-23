package com.example.newsapplicationchallenge.domain.use_case

import com.example.newsapplicationchallenge.data.network.common.Resource
import com.example.newsapplicationchallenge.domain.model.NewsItem
import com.example.newsapplicationchallenge.domain.model.NewsResponse

interface TopNewsUseCase {
    suspend operator fun invoke(): Resource<NewsResponse>
}