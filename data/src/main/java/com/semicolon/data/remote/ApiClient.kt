package com.semicolon.data.remote

import com.semicolon.data.model.TweetResponse
import javax.inject.Inject

class ApiClient@Inject constructor(private val apiService: ApiService) :
    RemoteSource {
    override suspend fun postTweet(status: String): TweetResponse {
        return apiService.postTweet(TweetRequest(status))
    }
}