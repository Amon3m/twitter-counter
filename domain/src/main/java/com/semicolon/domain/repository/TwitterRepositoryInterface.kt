package com.semicolon.domain.repository

import com.semicolon.domain.entity.Tweet
import com.semicolon.domain.util.Response
import kotlinx.coroutines.flow.Flow

interface TwitterRepositoryInterface {
    suspend fun postTweet(status: String): Flow<Response<Tweet>>
}