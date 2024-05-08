package com.ierusalem.dictionary.features.home.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.exclude
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ScaffoldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ierusalem.dictionary.ui.components.DictionaryAppBar
import com.ierusalem.dictionary.ui.components.WordItem
import com.ierusalem.dictionary.features.home.domain.HomeScreenState
import com.ierusalem.dictionary.ui.theme.DictionaryTheme
import com.ierusalem.dictionary.core.utils.Constants
import com.ierusalem.dictionary.ui.components.EmptyScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeContent(
    uiState: HomeScreenState,
    modifier: Modifier = Modifier,
    onNavIconPressed: () -> Unit = { },
    onSearchClick:() ->Unit,
    onItemClick:(String) -> Unit,
) {
    val topBarState = rememberTopAppBarState()
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(topBarState)

    Scaffold(
        topBar = {
            DictionaryAppBar(
                title = {
                    Text(
                        text = "Dictionary",
                        style = MaterialTheme.typography.titleLarge,
                        color = MaterialTheme.colorScheme.onBackground,
                        textAlign = TextAlign.Center
                    )
                },
                onNavIconPressed = onNavIconPressed,
                scrollBehavior = scrollBehavior,
                actions = {
                    Icon(
                        imageVector = Icons.Outlined.Search,
                        tint = MaterialTheme.colorScheme.onSurfaceVariant,
                        modifier = Modifier
                            .clickable(onClick = { onSearchClick() })
                            .padding(horizontal = 12.dp, vertical = 16.dp)
                            .height(24.dp),
                        contentDescription = null
                    )
                }
            )
        },
        // Exclude ime and navigation bar padding so this can be added by the UserInput composable
        contentWindowInsets = ScaffoldDefaults
            .contentWindowInsets
            .exclude(WindowInsets.navigationBars)
            .exclude(WindowInsets.ime),
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection)
    ) { paddingValues ->
        Column(
            Modifier
                .fillMaxSize()
                .padding(paddingValues),
            content = {
                if (uiState.words.isNotEmpty()) {
                    LazyColumn {
                        itemsIndexed(uiState.words) { index, item ->
                            WordItem(
                                onWordClicked = { /*TODO*/ },
                                onVoiceClick = { /*TODO*/ },
                                word = item
                            )
                            if (index < uiState.words.size - 1) {
                                HorizontalDivider(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(horizontal = 16.dp)
                                )
                            }
                        }
                    }
                } else {
                    EmptyScreen(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(color = MaterialTheme.colorScheme.background)
                    )
                }
            }
        )
    }

}

@Preview
@Composable
private fun HomeScreenPreview() {
    DictionaryTheme {
        HomeContent(
            uiState = HomeScreenState(Constants.PREVIEW_WORDS_DATA),
            onSearchClick = {},
            onItemClick = {},
            onNavIconPressed = {}
        )
    }
}

@Preview
@Composable
private fun HomeScreenPreviewDark() {
    DictionaryTheme(darkTheme = true) {
        HomeContent(
            uiState = HomeScreenState(Constants.PREVIEW_WORDS_DATA),
            onSearchClick = {},
            onItemClick = {},
            onNavIconPressed = {}
        )
    }
}