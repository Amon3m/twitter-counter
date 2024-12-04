package com.semicolon.twittercounter.ui.counter.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.semicolon.domain.entity.Tweet
import com.semicolon.domain.usecase.PostTweetUseCase
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
    private val useCase: PostTweetUseCase
) : ViewModel() {
    private val _tweetState: MutableStateFlow<TweetState.Display> =
        MutableStateFlow(TweetState.Display())
    val tweetState = _tweetState.asStateFlow()
    private val _errorState: MutableStateFlow<TweetState.Failure> =
        MutableStateFlow(TweetState.Failure())
    val errorState = _errorState.asStateFlow()

    init {
        postTweet("Hello World")
    }

    fun postTweet(status: String) {
        _tweetState.value = TweetState.Display(loading = true)

        viewModelScope.launch {
            useCase(status).onEach { result ->
                when (result) {
                    is Response.Success -> {
                        val tweet = result.data ?: Tweet()
                        _tweetState.value =
                            TweetState.Display(tweetUIModel = tweet, loading = false)
                        Log.d("TweetViewModel", "Success: ${result.data}")
                    }

                    is Response.Failure -> {
                        _errorState.value =
                            TweetState.Failure(errorMsg = result.error ?: "Unknown error")
                        Log.d("TweetViewModel", "Error: ${result.error}")
                    }

                    else -> {
                        _errorState.value = TweetState.Failure(errorMsg = "Unexpected error")
                        Log.d("TweetViewModel", "Unexpected error")
                    }
                }
            }.launchIn(viewModelScope)
        }
    }
}