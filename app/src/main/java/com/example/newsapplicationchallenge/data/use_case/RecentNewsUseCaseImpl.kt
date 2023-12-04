package com.example.newsapplicationchallenge.data.use_case

import com.example.newsapplicationchallenge.data.network.common.Resource
import com.example.newsapplicationchallenge.domain.model.NewsResponse
import com.example.newsapplicationchallenge.domain.repository.NewsRepository
import com.example.newsapplicationchallenge.domain.use_case.RecentNewsUseCase
import javax.inject.Inject

class RecentNewsUseCaseImpl @Inject constructor(private val newsRepository: NewsRepository) :
    RecentNewsUseCase {
    override suspend fun invoke(): Resource<NewsResponse> {
        return when (val response = newsRepository.getRecentNews()) {
            is Resource.Success -> {
                if (response.data.status == "ok") Resource.Success(response.data)
                else Resource.Error(response.data.status)
            }

            is Resource.Error -> {
                Resource.Error(response.message)
            }

            else -> {
                Resource.Error("Unknown error")
            }
        }
    }
}