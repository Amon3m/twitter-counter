package com.semicolon.data.remote

import com.semicolon.data.model.TweetResponse

interface RemoteSource {
    suspend fun postTweet(status: String): TweetResponse
}