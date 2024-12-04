package com.semicolon.twittercounter.ui.counter.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.semicolon.A.ui.theme.Theme

@Composable
fun CharacterCounterWindow(modifier: Modifier,isRemaining: Boolean, count: Int) {
    Column (
        modifier = modifier
            .height(110.dp)
            .border(
                width = 1.dp,
                color = Theme.colors.primaryLight,
                shape = RoundedCornerShape(Theme.radius.large)
            )
            .clip(RoundedCornerShape(Theme.radius.large))
    )
    {
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .background(Theme.colors.primaryLight),
            horizontalArrangement = Arrangement.Center,
        ){
            Text(
                text = if (isRemaining) "Characters Remaining" else "Characters Typed",
                style = Theme.typography.title,
                color = Theme.colors.black,
                textAlign = androidx.compose.ui.text.style.TextAlign.Center,
                modifier = Modifier.padding(8.dp)
            )
        }
        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ){
            Text(
                text = if (isRemaining) "$count" else "$count/280",
                style = Theme.typography.titleLarge,
                color = Theme.colors.black,
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
fun CharacterCounterWindowPreview() {
    CharacterCounterWindow( modifier = Modifier,isRemaining =false, 100)
}