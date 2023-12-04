package com.example.newsapplicationchallenge.presentation.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapplicationchallenge.data.network.common.Resource
import com.example.newsapplicationchallenge.domain.model.NewsItem
import com.example.newsapplicationchallenge.domain.use_case.RecentNewsUseCase
import com.example.newsapplicationchallenge.domain.use_case.PopularNewsUseCase
import com.example.newsapplicationchallenge.domain.use_case.TopNewsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val topNewsUseCase: TopNewsUseCase,
    private val allNewsUseCase: RecentNewsUseCase,
    private val popularNews: PopularNewsUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState>
        get() = _uiState

    init {
        getTopHeadlines().onAwait
        getRecentNews().onAwait
        getPopularNews().onAwait
    }

    private fun getTopHeadlines(): Deferred<Unit> {

        _uiState.update { currentUiState ->
            currentUiState.copy(
                isLoading = true
            )
        }

        return viewModelScope.async {
            when (val response = topNewsUseCase()) {
                is Resource.Success -> {
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            isSuccess = true,
                            topHeadlines = response.data.articles
                        )
                    }
                }

                else -> {
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            isSuccess = true,
                            topHeadlines = emptyList()
                        )
                    }
                }
            }
        }
    }

    private fun getRecentNews(): Deferred<Unit> {
        _uiState.update { currentUiState ->
            currentUiState.copy(
                isLoading = true
            )
        }
        return viewModelScope.async {
            when (val response = allNewsUseCase()) {
                is Resource.Success -> {
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            isSuccess = true,
                            recentNews = response.data.articles
                        )
                    }
                }

                else -> {
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            isSuccess = true,
                            recentNews = emptyList()
                        )
                    }
                }
            }
        }
    }

    private fun getPopularNews(): Deferred<Unit> {

        _uiState.update { currentUiState ->
            currentUiState.copy(
                isLoading = true
            )
        }

        return viewModelScope.async {
            when (val response = popularNews()) {
                is Resource.Success -> {
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            isSuccess = true,
                            popularNews = response.data.articles
                        )
                    }
                }

                else -> {
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            isSuccess = true,
                            popularNews = emptyList()
                        )
                    }
                }
            }
        }
    }
}

data class HomeUiState(
    val isLoading: Boolean = false,
    val isSuccess: Boolean = false,
    val topHeadlines: List<NewsItem> = emptyList(),
    val recentNews: List<NewsItem> = emptyList(),
    val popularNews: List<NewsItem> = emptyList()
)