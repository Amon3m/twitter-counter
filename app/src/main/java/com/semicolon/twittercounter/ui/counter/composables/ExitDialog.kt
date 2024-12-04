package com.semicolon.twittercounter.ui.counter.composables

import android.app.Activity
import android.content.Context
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.tooling.preview.Preview
import com.semicolon.A.ui.theme.Theme

@Composable
fun ExitDialog(
    context: Context,
    showDialog: MutableState<Boolean>
) {
    if (showDialog.value) {
        AlertDialog(
            containerColor = Theme.colors.white,
            onDismissRequest = { showDialog.value = false },
            title = { Text(text = "Exit App") },
            text = { Text(text = "Are you sure you want to exit the app?") },
            confirmButton = {
                Button(
                    colors = ButtonDefaults.buttonColors(containerColor = Theme.colors.red),
                    onClick = {
                    showDialog.value = false
                    (context as Activity).finish()
                }) {
                    Text("Yes")
                }
            },
            dismissButton = {
                Button(
                    colors = ButtonDefaults.buttonColors(containerColor = Theme.colors.primary),
                    onClick = { showDialog.value = false }
                ) {
                    Text("No")
                }
            }
        )
    }
}
