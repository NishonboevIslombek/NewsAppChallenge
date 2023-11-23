package com.example.newsapplicationchallenge.di.module

import com.example.newsapplicationchallenge.data.repository.NewsRepositoryImpl
import com.example.newsapplicationchallenge.domain.repository.NewsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
interface RepositoryModule {
    @Binds
    fun bindRepository(repository: NewsRepositoryImpl): NewsRepository
}