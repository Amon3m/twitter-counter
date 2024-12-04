package com.semicolon.domain.usecase

import com.semicolon.domain.repository.TwitterRepositoryInterface

class PostTweetUseCase(
    private val repository: TwitterRepositoryInterface
) {
    suspend operator fun invoke(status: String) = repository.postTweet(status)
}