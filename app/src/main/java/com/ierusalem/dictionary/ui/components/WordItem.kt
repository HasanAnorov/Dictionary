package com.ierusalem.dictionary.ui.components

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
import com.ierusalem.dictionary.ui.theme.DictionaryTheme

@Composable
fun WordItem(
    modifier: Modifier = Modifier,
    onWordClicked: () -> Unit,
    onVoiceClick: (String) -> Unit,
    word: String,
    audio:String?,
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
                .padding(vertical = 16.dp)
                .padding(start = 16.dp),
            style = MaterialTheme.typography.titleSmall,
            color = MaterialTheme.colorScheme.onBackground
        )
        if(audio!=null){
            IconButton(onClick = { onVoiceClick(audio) }) {
                Icon(
                    painter = painterResource(id = R.drawable.voice_cricle),
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onBackground
                )
            }
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
            onVoiceClick = {},
            audio = null
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
            onVoiceClick = {},
            audio = ""
        )
    }
}