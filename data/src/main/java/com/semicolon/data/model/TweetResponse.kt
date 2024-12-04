package com.semicolon.data.model

import com.google.gson.annotations.SerializedName

data class TweetResponse(

	@field:SerializedName("data")
	val data: Data? = null
)

data class Data(

	@field:SerializedName("edit_history_tweet_ids")
	val editHistoryTweetIds: List<String?>? = null,

	@field:SerializedName("id")
	val id: String? = null,

	@field:SerializedName("text")
	val text: String? = null
)
