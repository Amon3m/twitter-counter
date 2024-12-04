package com.semicolon.twittercounter.di

import com.semicolon.domain.repository.TwitterRepositoryInterface
import com.semicolon.domain.usecase.PostTweetUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.components.SingletonComponent

//@Module
//@InstallIn(ViewModelComponent::class)
//object UseCaseModule {
//    @Provides
//    fun providePostTweetUseCase(repository: TwitterRepositoryInterface): PostTweetUseCase {
//        return PostTweetUseCase(repository)
//    }
//}
@Module
@InstallIn(SingletonComponent::class)
class UseCaseModule {

    @Provides
    fun providePostTweetUseCase(
        repository: TwitterRepositoryInterface
    ): PostTweetUseCase {
        return PostTweetUseCase(repository)
    }
}