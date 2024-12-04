package com.semicolon.data.repository

import com.semicolon.data.mapper.toTweet
import com.semicolon.data.remote.RemoteSource
import com.semicolon.domain.entity.Tweet
import com.semicolon.domain.repository.TwitterRepositoryInterface
import com.semicolon.domain.util.Response
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class TwitterRepository  @Inject constructor(
    private val remoteSource: RemoteSource
) : TwitterRepositoryInterface {
    override suspend fun postTweet(status: String): Flow< Response<Tweet>>  = flow {
        try {
            val tweetResponse = remoteSource.postTweet(status)
            val tweet = tweetResponse.toTweet()
            emit(Response.Success(tweet))
        } catch (e: Exception) {
            emit(Response.Failure(e.message ?: "Unknown error"))
        }
    }
}