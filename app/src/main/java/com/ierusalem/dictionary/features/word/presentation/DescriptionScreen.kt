package com.ierusalem.dictionary.features.word.presentation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ierusalem.dictionary.components.DictionaryAppBar
import com.ierusalem.dictionary.theme.DictionaryTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DescriptionScreen() {
    Scaffold(
        topBar = {
            DictionaryAppBar(
                title = {},
                onNavIconPressed = { },
                actions = {
                    Icon(
                        imageVector = Icons.Outlined.Search,
                        tint = MaterialTheme.colorScheme.onSurfaceVariant,
                        modifier = Modifier
                            .clickable(onClick = { })
                            .padding(horizontal = 12.dp, vertical = 16.dp)
                            .height(24.dp),
                        contentDescription = null
                    )
                }
            )
        }
    ) {
        Column(
            modifier = Modifier
                .padding(it)
                .fillMaxSize()
        ) {
            Column {

            }
            LazyColumn {

            }
        }
    }
}


@Preview
@Composable
private fun DescriptionScreenPreviewLight() {
    DictionaryTheme {
        DescriptionScreen()
    }
}

@Preview
@Composable
private fun DescriptionScreenPreviewDark() {
    DictionaryTheme(darkTheme = true) {
        DescriptionScreen()
    }
}