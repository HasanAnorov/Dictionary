package com.ierusalem.dictionary.features.about.presentation

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ierusalem.dictionary.ui.components.DictionaryAppBar
import com.ierusalem.dictionary.ui.theme.DictionaryTheme
import com.ireward.htmlcompose.HtmlText

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AboutScreen(
    modifier: Modifier = Modifier,
    onNavIconPressed: () -> Unit,
    content: String
) {
    val scrollState = rememberScrollState()
    Column(
        modifier = modifier
            .verticalScroll(scrollState)
            .background(MaterialTheme.colorScheme.background),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Top,
    ) {
        DictionaryAppBar(
            title = {
                Text(
                    text = "About",
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.onBackground,
                    textAlign = TextAlign.Center
                )
            },
            navIcon = Icons.AutoMirrored.Filled.ArrowBack,
            onNavIconPressed = onNavIconPressed,
        )
        HtmlText(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .padding(bottom = 16.dp),
            text = content,
            style = MaterialTheme.typography.bodyMedium,
            linkClicked = { link ->
                Log.d("linkClicked", link)
            }
        )
    }
}

@Preview
@Composable
private fun PreviewAbout() {
    DictionaryTheme {
        AboutScreen(
            modifier = Modifier.fillMaxSize(),
            content = "<i>This text is italic</i>",
            onNavIconPressed = {}
        )
    }
}