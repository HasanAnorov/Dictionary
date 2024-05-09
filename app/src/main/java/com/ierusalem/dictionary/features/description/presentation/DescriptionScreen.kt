package com.ierusalem.dictionary.features.description.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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


@Composable
fun MainScreen(
    paddingValues: PaddingValues,
    wordModel: WordModel
) {
    Box(
        modifier = Modifier
            .padding(paddingValues)
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        content = {

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(80.dp)
                    .background(MaterialTheme.colorScheme.background)
                    .padding(horizontal = 30.dp)
            ) {

                Spacer(modifier = Modifier.height(20.dp))

                Text(
                    text = wordModel.word,
                    fontSize = 30.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.primary
                )

                Spacer(modifier = Modifier.height(8.dp))

                if(wordModel.definition != null){
                    Text(
                        text = wordModel.definition ,
                        fontSize = 17.sp,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                }

                Spacer(modifier = Modifier.height(20.dp))
            }

            Box(
                modifier = Modifier
                    .padding(top = 110.dp)
                    .fillMaxSize()
                    .clip(
                        RoundedCornerShape(
                            topStart = 50.dp,
                            topEnd = 50.dp
                        )
                    )
                    .background(
                        MaterialTheme.colorScheme.secondaryContainer.copy(0.7f)
                    )
            ) {
                WordResult(wordModel)
            }
        }
    )
}

@Composable
fun WordResult(wordItem: WordModel) {
    LazyColumn(
        modifier = Modifier.padding(top = 32.dp)
//        contentPadding = PaddingValues(vertical = 32.dp)
    ) {
        itemsIndexed(wordItem.translations) {index,  item ->
            Meaning(
                translation = item,
                index = index
            )

            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}

@Composable
fun Meaning(
    translation:String,
    index: Int
) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {

        Text(
            text = "${index + 1}. $translation",
            fontSize = 17.sp,
            color = MaterialTheme.colorScheme.onPrimary,
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(20.dp))
                .background(
                    brush = Brush.horizontalGradient(
                        listOf(
                            MaterialTheme.colorScheme.primary,
                            MaterialTheme.colorScheme.primary.copy(0.4f),
                            Color.Transparent
                        )
                    )
                )
                .padding(
                    top = 2.dp, bottom = 4.dp,
                    start = 12.dp, end = 12.dp
                )
        )
    }

}

@Preview
@Composable
private fun PreviewLight() {
    DictionaryTheme(darkTheme = true) {
        MainScreen(
            paddingValues = PaddingValues(),
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