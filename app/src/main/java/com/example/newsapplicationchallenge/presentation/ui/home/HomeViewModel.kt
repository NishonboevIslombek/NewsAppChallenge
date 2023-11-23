package com.example.newsapplicationchallenge.presentation.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapplicationchallenge.data.network.common.Resource
import com.example.newsapplicationchallenge.domain.model.NewsItem
import com.example.newsapplicationchallenge.domain.use_case.TopNewsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val topNewsUseCase: TopNewsUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState>
        get() = _uiState

    init {
        getNews()
    }

    private fun getNews() {

        _uiState.update { currentUiState ->
            currentUiState.copy(
                isLoading = true
            )
        }

        viewModelScope.launch {
            when (val response = topNewsUseCase()) {
                is Resource.Success -> {
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            isSuccess = true,
                            topNews = response.data.articles
                        )
                    }
                }

                else -> {
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            isSuccess = true,
                            topNews = emptyList()
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
    val topNews: List<NewsItem> = emptyList()
)