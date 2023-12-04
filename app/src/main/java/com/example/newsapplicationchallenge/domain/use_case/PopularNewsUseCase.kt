package com.example.newsapplicationchallenge.domain.use_case

import com.example.newsapplicationchallenge.data.network.common.Resource
import com.example.newsapplicationchallenge.domain.model.NewsResponse

interface PopularNewsUseCase {
    suspend operator fun invoke(): Resource<NewsResponse>
}