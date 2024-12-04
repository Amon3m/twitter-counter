package com.semicolon.domain.usecase


import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class ValidateTweetUseCaseTest {

    private lateinit var validateTweetUseCase: ValidateTweetUseCase

    @Before
    fun setUp() {
        validateTweetUseCase = ValidateTweetUseCase()
    }


    @Test
    fun `isValidTweet returns true for valid tweet`() {
        val text = "This is a valid tweet"
        val isValid = validateTweetUseCase.isValidTweet(text)

        assertTrue(isValid)
    }

    @Test
    fun `isValidTweet returns false for over length tweet`() {
        val text = "a".repeat(281)
        val isValid = validateTweetUseCase.isValidTweet(text)

        assertEquals(false, isValid)
    }

    @Test
    fun `isValidTweet returns false for empty tweet`() {
        val text = ""
        val isValid = validateTweetUseCase.isValidTweet(text)

        assertEquals(false, isValid)
    }

}
