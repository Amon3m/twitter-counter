package com.semicolon.twittercounter.ui.counter.bar

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.semicolon.A.ui.theme.Theme
import com.semicolon.twittercounter.R

@Composable
fun TopBar(
    modifier: Modifier = Modifier,
    text: String,
    onClickBack: () -> Unit
) {
    Box(
        modifier = modifier
            .height(50.dp)
            .fillMaxWidth()
            .background(Theme.colors.white)
            .padding(horizontal = 16.dp),
    )
    {
        Text(
            modifier = Modifier
                .align(Alignment.Center),
            text = text,
            style = Theme.typography.header,
            maxLines = 1,
            color = Theme.colors.titleDarkGray
        )
        Icon(
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .padding(start = 16.dp)
                .clickable { onClickBack() },
            painter = painterResource(id = R.drawable.arrow_ic),
            contentDescription = "Back",
            tint = Theme.colors.dimGray,
        )
    }
}

@Composable
@Preview(showBackground = true)
private fun AppBarPreview() {
    TopBar( text = "Counter", onClickBack = {})
}