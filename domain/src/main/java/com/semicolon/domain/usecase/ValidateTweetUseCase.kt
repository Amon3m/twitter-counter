package com.semicolon.domain.usecase

import com.semicolon.domain.util.TweetValidator

class ValidateTweetUseCase {
    fun execute(text: String): Int {
        return TweetValidator.countCharacters(text)
    }
    fun isValidTweet(text: String): Boolean {
        return TweetValidator.isValidTweet(text)
    }
}