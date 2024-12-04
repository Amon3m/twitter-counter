package com.semicolon.twittercounter.ui.counter.viewmodel

import app.cash.turbine.test
import com.semicolon.domain.usecase.PostTweetUseCase
import com.semicolon.domain.usecase.ValidateTweetUseCase
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class TweetViewModelTest {

    private lateinit var postTweetUseCase: PostTweetUseCase
    private lateinit var validateTweetUseCase: ValidateTweetUseCase
    private lateinit var tweetViewModel: TweetViewModel

    @Before
    fun setUp() {
        postTweetUseCase = mockk()
        validateTweetUseCase = mockk()
        tweetViewModel = TweetViewModel(postTweetUseCase, validateTweetUseCase)
    }

    @Test
    fun `onTweetTextChanged updates tweetText and charactersTyped`() = runTest {
        val tweetText = "This is a test tweet"
        val characterCount = tweetText.length

        every { validateTweetUseCase.execute(tweetText) } returns characterCount

        tweetViewModel.onTweetTextChanged(tweetText)

        tweetViewModel.tweetText.test {
            assertEquals(tweetText, awaitItem())
        }

        tweetViewModel.charactersTyped.test {
            assertEquals(characterCount, awaitItem())
        }
    }

    @Test
    fun `isTweetValid returns true for valid tweet`() {
        val tweetText = "Valid tweet text"

        every { validateTweetUseCase.execute(tweetText) } returns tweetText.length
        every { validateTweetUseCase.isValidTweet(tweetText) } returns true

        tweetViewModel.onTweetTextChanged(tweetText)

        assertTrue(tweetViewModel.isTweetValid())
    }

    @Test
    fun `isTweetValid returns false for invalid tweet`() {
        val tweetText = "Invalid tweet text"

        every { validateTweetUseCase.execute(tweetText) } returns tweetText.length
        every { validateTweetUseCase.isValidTweet(tweetText) } returns false

        tweetViewModel.onTweetTextChanged(tweetText)

        assertFalse(tweetViewModel.isTweetValid())
    }

    @Test
    fun `clearTweetText resets tweetText and charactersTyped`() = runTest {
        tweetViewModel.clearTweetText()

        tweetViewModel.tweetText.test {
            assertEquals("", awaitItem())
        }

        tweetViewModel.charactersTyped.test {
            assertEquals(0, awaitItem())
        }
    }
}