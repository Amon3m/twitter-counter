package com.semicolon.twittercounter.di

import com.semicolon.domain.repository.TwitterRepositoryInterface
import com.semicolon.domain.usecase.PostTweetUseCase
import com.semicolon.domain.usecase.ValidateTweetUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
class UseCaseModule {
    @Provides
    fun providePostTweetUseCase(
        repository: TwitterRepositoryInterface
    ): PostTweetUseCase {
        return PostTweetUseCase(repository)
    }
    @Provides
    fun provideValidateTweetUseCase(): ValidateTweetUseCase {
        return ValidateTweetUseCase()
    }
}