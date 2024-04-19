package com.ierusalem.dictionary.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ierusalem.dictionary.R
import com.ierusalem.dictionary.theme.DictionaryTheme

@Composable
fun WordItem(
    modifier: Modifier = Modifier,
    onWordClicked: () -> Unit,
    onVoiceClick: () -> Unit,
    word: String
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.background)
            .clickable { onWordClicked() },
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = word,
            modifier = Modifier
                .weight(1F)
                .padding(start = 16.dp),
            style = MaterialTheme.typography.titleSmall,
            color = MaterialTheme.colorScheme.onBackground
        )
        IconButton(onClick = { onVoiceClick() }) {
            Icon(
                painter = painterResource(id = R.drawable.voice_cricle),
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onBackground
            )
        }
    }
}

@Preview
@Composable
private fun WordItemLightPreview() {
    DictionaryTheme {
        WordItem(
            word = "Word here",
            onWordClicked = {},
            onVoiceClick = {}
        )
    }
}

@Preview
@Composable
private fun WordItemLightPreviewDark() {
    DictionaryTheme(darkTheme = true) {
        WordItem(
            word = "Word here",
            onWordClicked = {},
            onVoiceClick = {}
        )
    }
}