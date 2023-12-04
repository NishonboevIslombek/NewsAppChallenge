package com.example.newsapplicationchallenge.data.repository

import com.example.newsapplicationchallenge.data.network.NewsApi
import com.example.newsapplicationchallenge.data.network.common.Resource
import com.example.newsapplicationchallenge.domain.model.NewsResponse
import com.example.newsapplicationchallenge.domain.repository.NewsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    private val newsApi: NewsApi
) : NewsRepository {
    override suspend fun getTopHeadlines(): Resource<NewsResponse> =
        withContext(Dispatchers.IO) {
            return@withContext try {
                Resource.Success(newsApi.getTopHeadlines())
            } catch (throwable: Throwable) {
                Resource.Error(throwable.message ?: "")
            }
        }

    override suspend fun getRecentNews(): Resource<NewsResponse> =
        withContext(Dispatchers.IO) {
            return@withContext try {
                Resource.Success(newsApi.getRecentNews())
            } catch (throwable: Throwable) {
                Resource.Error(throwable.message ?: "")
            }
        }

    override suspend fun getPopularNews(): Resource<NewsResponse> =
        withContext(Dispatchers.IO) {
            return@withContext try {
                Resource.Success(newsApi.getPopularNews())
            } catch (throwable: Throwable) {
                Resource.Error(throwable.message ?: "")
            }
        }
}