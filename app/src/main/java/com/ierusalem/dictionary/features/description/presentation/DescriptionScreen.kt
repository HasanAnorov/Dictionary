package com.ierusalem.dictionary.features.description.presentation

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.ierusalem.dictionary.features.landing.db.WordModel
import com.ierusalem.dictionary.ui.components.ErrorScreen
import com.ierusalem.dictionary.ui.theme.DictionaryTheme

@Composable
fun DescriptionScreen(wordModel: WordModel?) {
    Scaffold { paddingValues ->
        if(wordModel == null){
            ErrorScreen()
        }else{
            MainScreen(wordModel = wordModel, paddingValues = paddingValues )
        }
    }
}


@Preview
@Composable
private fun DescriptionScreenPreviewLight() {
    DictionaryTheme {
        DescriptionScreen(
            wordModel = WordModel(
                word = "home",
                translations = listOf("uy", "home", "house"),
                category = "Home",
                audio = null,
                definition = null,
                language = "english"
            )
        )
    }
}

@Preview
@Composable
private fun DescriptionScreenPreviewDark() {
    DictionaryTheme(darkTheme = true) {
        DescriptionScreen(
            wordModel = WordModel(
                word = "home",
                translations = listOf("uy", "home", "house"),
                category = "Home",
                audio = null,
                definition = null,
                language = "english"
            )
        )
    }
}