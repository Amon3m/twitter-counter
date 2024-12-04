package com.semicolon.data.remote

import com.semicolon.data.model.TweetResponse
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ApiService {
    @POST("2/tweets")
    suspend fun postTweet(
        @Body body: TweetRequest
    ): TweetResponse
}
