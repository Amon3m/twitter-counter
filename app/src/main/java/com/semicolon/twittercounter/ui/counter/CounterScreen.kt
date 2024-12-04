package com.semicolon.twittercounter.ui.counter

import android.content.ClipboardManager
import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.semicolon.A.ui.theme.Theme
import com.semicolon.twittercounter.R
import com.semicolon.twittercounter.ui.counter.bar.TopBar
import com.semicolon.twittercounter.ui.counter.composables.CharacterCounterWindow
import com.semicolon.twittercounter.ui.counter.composables.ExitDialog
import com.semicolon.twittercounter.ui.counter.composables.TwitterButton
import com.semicolon.twittercounter.ui.counter.composables.TwitterTextField
import com.semicolon.twittercounter.ui.counter.viewmodel.TweetViewModel

@Composable
fun CounterScreen(viewModel: TweetViewModel = hiltViewModel()) {
    val tweetText by viewModel.tweetText.collectAsState()
    val tweetState by viewModel.tweetState.collectAsState()
    val errorState by viewModel.errorState.collectAsState()
    val charactersTyped by viewModel.charactersTyped.collectAsState()
    val charactersRemaining = 280 - charactersTyped
    val context = LocalContext.current
    val clipboardManager = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
    val showDialog = remember { mutableStateOf(false) }


    Scaffold(modifier = Modifier
        .safeDrawingPadding() ,
        containerColor= Theme.colors.white,
        topBar = {
            TopBar(text = stringResource(R.string.twitter_character_count),
                onClickBack = {
                    showDialog.value = true
                })
        })
    { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .padding(16.dp)
                .background(Theme.colors.white),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Image(
                painter = painterResource(id = R.drawable.twitter_logo),
                contentDescription = "Twitter Logo",
                modifier = Modifier.size(60.dp)
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                CharacterCounterWindow(
                    isRemaining = false,
                    count = charactersTyped,
                    modifier = Modifier.weight(1f)
                )
                CharacterCounterWindow(
                    isRemaining = true,
                    count = charactersRemaining,
                    modifier = Modifier.weight(1f)
                )
            }
            TwitterTextField(text = tweetText,
                onTextChange = { viewModel.onTweetTextChanged(it) },
                modifier = Modifier.padding(top = 16.dp))


            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                TwitterButton(
                    text = stringResource(R.string.copy_text),
                    backgroundColor = Theme.colors.red,
                    modifier = Modifier.padding(16.dp),
                    onClick = {
                        viewModel.copyTweetTextToClipboard(clipboardManager)
                    }
                )
                TwitterButton(
                    text = stringResource(R.string.clear_text),
                    backgroundColor = Theme.colors.green,
                    modifier = Modifier.padding(16.dp),
                    onClick = {
                        viewModel.clearTweetText()
                    }
                )
            }
            TwitterButton(
                text = stringResource(R.string.post_tweet),
                backgroundColor = Theme.colors.primary,
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                loading = tweetState.loading,
                onClick = {
                    when {
                        tweetText.isEmpty() -> {
                            Toast.makeText(
                                context,
                                "Tweet cannot be empty",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                        viewModel.isTweetValid() -> {
                            viewModel.postTweet(tweetText)
                        }
                        else -> {
                            Toast.makeText(
                                context,
                                "Tweet is too long",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                }
            )
        }
    }
    ExitDialog(context = context, showDialog = showDialog)

    LaunchedEffect(tweetState) {
        if (tweetState.tweetUIModel.text?.isNotEmpty() == true) {
            Toast.makeText(context, "Tweet posted successfully", Toast.LENGTH_SHORT).show()
        }
    }

    LaunchedEffect(errorState) {
        if (errorState.errorMsg.isNotEmpty()) {
            Toast.makeText(context, errorState.errorMsg, Toast.LENGTH_SHORT).show()
            viewModel.resetErrorState()

        }
    }
}

@Composable
@Preview(showBackground = true)
private fun CounterScreenPreview() {
    CounterScreen()
}