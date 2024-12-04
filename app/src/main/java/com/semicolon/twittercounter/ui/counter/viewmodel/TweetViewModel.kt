package com.semicolon.twittercounter.ui.counter.viewmodel

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.semicolon.domain.entity.Tweet
import com.semicolon.domain.usecase.PostTweetUseCase
import com.semicolon.domain.usecase.ValidateTweetUseCase
import com.semicolon.domain.util.Response
import com.semicolon.twittercounter.ui.counter.state.TweetState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TweetViewModel @Inject constructor(
    private val useCase: PostTweetUseCase,
    private val validateTweetUseCase: ValidateTweetUseCase

) : ViewModel() {
    private val _tweetState: MutableStateFlow<TweetState.Display> =
        MutableStateFlow(TweetState.Display( loading = false))
    val tweetState = _tweetState.asStateFlow()
    private val _errorState: MutableStateFlow<TweetState.Failure> =
        MutableStateFlow(TweetState.Failure())
    val errorState = _errorState.asStateFlow()


    private val _tweetText = MutableStateFlow("")
    val tweetText = _tweetText.asStateFlow()

    private val _charactersTyped = MutableStateFlow(0)
    val charactersTyped = _charactersTyped.asStateFlow()

    fun onTweetTextChanged(newText: String) {
        _tweetText.value = newText
        _charactersTyped.value = validateTweetUseCase.execute(newText)
    }

    fun isTweetValid(): Boolean {
        return validateTweetUseCase.isValidTweet(_tweetText.value)
    }
    fun postTweet(status: String) {
        _tweetState.value = TweetState.Display(loading = true)

        viewModelScope.launch {
            useCase(status).onEach { result ->
                when (result) {
                    is Response.Success -> {
                        _tweetState.value = TweetState.Display(loading = false)

                        val tweet = result.data ?: Tweet()
                        _tweetState.value =
                            TweetState.Display(tweetUIModel = tweet, loading = false)
                    }

                    is Response.Failure -> {
                        _errorState.value =
                            TweetState.Failure(errorMsg = result.error ?: "Unknown error")
                        Log.d("TweetViewModel", "Error: ${result.error}")
                        _tweetState.value = TweetState.Display(loading = false)
                    }

                    else -> {
                        _errorState.value = TweetState.Failure(errorMsg = "Unexpected error")
                        Log.e("TweetViewModel", "Unexpected error")
                        _tweetState.value = TweetState.Display(loading = false)
                    }
                }
            }.launchIn(viewModelScope)
        }
    }
    fun resetErrorState() {
        _errorState.value = TweetState.Failure()
    }
    fun copyTweetTextToClipboard(clipboardManager: ClipboardManager) {
        val clip = ClipData.newPlainText("Tweet Text", _tweetText.value)
        clipboardManager.setPrimaryClip(clip)
    }
    fun clearTweetText() {
        _tweetText.value = ""
        _charactersTyped.value = 0
    }

}