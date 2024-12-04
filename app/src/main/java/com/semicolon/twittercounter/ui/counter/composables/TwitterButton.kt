package com.semicolon.twittercounter.ui.counter.composables

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.semicolon.A.ui.theme.Theme

@Composable
fun TwitterButton(
    text: String,
    backgroundColor: Color =Theme.colors.white,
    modifier: Modifier,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        modifier =modifier ,
        shape = RoundedCornerShape(12.dp),
        colors = ButtonDefaults.buttonColors(containerColor =backgroundColor )
    ) {
        Text(text = text)
    }
}

@Composable
@Preview(showBackground = true)
private fun TwitterButtonPreview() {
    TwitterButton(
        text = "Copy Text",
        backgroundColor = Theme.colors.red,
        modifier = Modifier.padding(16.dp),
        onClick = {}
    )
}