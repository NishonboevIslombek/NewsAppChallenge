package com.example.newsapplicationchallenge.data.use_case

import com.example.newsapplicationchallenge.data.network.common.Resource
import com.example.newsapplicationchallenge.domain.model.NewsItem
import com.example.newsapplicationchallenge.domain.model.NewsResponse
import com.example.newsapplicationchallenge.domain.repository.NewsRepository
import com.example.newsapplicationchallenge.domain.use_case.TopNewsUseCase
import javax.inject.Inject

class TopNewsUseCaseImpl @Inject constructor(
    private val newsRepository: NewsRepository
) : TopNewsUseCase {
    override suspend fun invoke(): Resource<NewsResponse> {
        return when (val response = newsRepository.getTopHeadlines()) {
            is Resource.Success -> {
                Resource.Success(response.data)
            }

            is Resource.Error -> {
                Resource.Error(response.message)
            }

            else -> {
                Resource.Error("Unknown Error occurred")
            }
        }
    }
}