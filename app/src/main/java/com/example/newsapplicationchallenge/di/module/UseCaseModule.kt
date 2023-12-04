package com.example.newsapplicationchallenge.di.module

import com.example.newsapplicationchallenge.data.use_case.RecentNewsUseCaseImpl
import com.example.newsapplicationchallenge.data.use_case.PopularNewsUseCaseImpl
import com.example.newsapplicationchallenge.data.use_case.TopNewsUseCaseImpl
import com.example.newsapplicationchallenge.domain.use_case.RecentNewsUseCase
import com.example.newsapplicationchallenge.domain.use_case.PopularNewsUseCase
import com.example.newsapplicationchallenge.domain.use_case.TopNewsUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
interface UseCaseModule {
    @Binds
    fun bindTopNewsUseCase(impl: TopNewsUseCaseImpl): TopNewsUseCase

    @Binds
    fun bindRecentNewsUseCase(impl: RecentNewsUseCaseImpl): RecentNewsUseCase

    @Binds
    fun bindPopularNewsUseCase(impl: PopularNewsUseCaseImpl): PopularNewsUseCase
}