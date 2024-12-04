package com.semicolon.twittercounter.ui.counter

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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.semicolon.A.ui.theme.Theme
import com.semicolon.twittercounter.R
import com.semicolon.twittercounter.ui.counter.bar.TopBar
import com.semicolon.twittercounter.ui.counter.composables.CharacterCounterWindow
import com.semicolon.twittercounter.ui.counter.composables.TwitterButton
import com.semicolon.twittercounter.ui.counter.composables.TwitterTextField
import com.semicolon.twittercounter.ui.counter.viewmodel.TweetViewModel

@Composable
fun CounterScreen(viewModel: TweetViewModel = hiltViewModel()) {
    viewModel.postTweet("Hello")
    Scaffold(modifier = Modifier
        .safeDrawingPadding() ,
        containerColor= Theme.colors.white,
        topBar = {
            TopBar(text = stringResource(R.string.twitter_character_count),
                onClickBack = {})
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
                    count = 0,
                    modifier = Modifier.weight(1f)
                )
                CharacterCounterWindow(
                    isRemaining = true,
                    count = 280,
                    modifier = Modifier.weight(1f)
                )
            }
            TwitterTextField(modifier = Modifier.padding(top = 16.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                TwitterButton(
                    text = stringResource(R.string.copy_text),
                    backgroundColor = Theme.colors.red,
                    modifier = Modifier.padding(16.dp),
                    onClick = {}
                )
                TwitterButton(
                    text = stringResource(R.string.clear_text),
                    backgroundColor = Theme.colors.green,
                    modifier = Modifier.padding(16.dp),
                    onClick = {}
                )
            }
            TwitterButton(
                text = stringResource(R.string.post_tweet),
                backgroundColor = Theme.colors.primary,
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                onClick = {}
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
private fun CounterScreenPreview() {
    CounterScreen()
}