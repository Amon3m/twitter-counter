package com.semicolon.twittercounter.ui.counter.state

import com.semicolon.domain.entity.Tweet

sealed class TweetState {
    data class Display(
        var tweetUIModel: Tweet = Tweet(),
        var loading: Boolean = false
    ) : TweetState()

    data class Failure(val errorMsg: String = "") : TweetState()}