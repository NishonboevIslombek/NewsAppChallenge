package com.example.newsapplicationchallenge.di.module

import com.example.newsapplicationchallenge.data.use_case.TopNewsUseCaseImpl
import com.example.newsapplicationchallenge.domain.use_case.TopNewsUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
@InstallIn(SingletonComponent::class)
@Module
interface UseCaseModule {
    @Binds
     fun bindTopNewsUseCase(repository: TopNewsUseCaseImpl): TopNewsUseCase
}