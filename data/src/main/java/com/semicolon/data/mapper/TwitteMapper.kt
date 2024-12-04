package com.semicolon.data.mapper

import com.semicolon.data.model.TweetResponse
import com.semicolon.domain.entity.Tweet

fun TweetResponse.toTweet(): Tweet {
    return Tweet(
        id = data?.id,
        text = data?.text,
    )
}