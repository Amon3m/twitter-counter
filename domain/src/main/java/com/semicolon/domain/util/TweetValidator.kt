package com.semicolon.domain.util

object TweetValidator {

    private const val MAX_TWEET_LENGTH = 280
    private const val URL_LENGTH = 23

    fun countCharacters(text: String): Int {
        var count = 0
        val urlRegex = Regex("(https?://\\S+)")
        val urls = urlRegex.findAll(text).toList()

        var remainingText = text
        for (url in urls) {
            count += URL_LENGTH
            remainingText = remainingText.replace(url.value, "")
        }

        for (char in remainingText) {
            count += when {
                char.isSurrogate() -> 2
                char.isWhitespace() || char.isLetterOrDigit() || char.isPunctuation() -> 1
                else -> 1
            }
        }

        return count
    }

    fun isValidTweet(text: String): Boolean {
        val characterCount = countCharacters(text)
        return characterCount in 1..MAX_TWEET_LENGTH
    }

    private fun Char.isSurrogate(): Boolean {
        return this in '\uD800'..'\uDFFF'
    }

    private fun Char.isPunctuation(): Boolean {
        return this in listOf('.', ',', '!', '?', ';', ':', '-', '(', ')', '[', ']', '{', '}', '\'', '\"')
    }
}