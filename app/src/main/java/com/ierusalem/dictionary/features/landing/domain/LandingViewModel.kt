package com.ierusalem.dictionary.features.landing.domain

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ierusalem.androchat.ui.navigation.DefaultNavigationEventDelegate
import com.ierusalem.androchat.ui.navigation.NavigationEventDelegate
import com.ierusalem.androchat.ui.navigation.emitNavigation
import com.ierusalem.dictionary.core.utils.Constants
import com.ierusalem.dictionary.features.landing.db.WordModel
//import com.ierusalem.dictionary.features.landing.db.WordModel
import com.ierusalem.dictionary.features.landing.db.WordsDao
import com.ierusalem.dictionary.features.landing.presentation.LandingPageNavigation
import com.ierusalem.dictionary.features.landing.presentation.model.WordItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.annotation.concurrent.Immutable
import javax.inject.Inject

@HiltViewModel
class LandingViewModel @Inject constructor(
    private val repository: WordsRepository,
) : ViewModel(),
    NavigationEventDelegate<LandingPageNavigation> by DefaultNavigationEventDelegate() {

    private val handler = CoroutineExceptionHandler { _, exception ->
        Log.d("ahi3646", " coroutineExceptionHandler : error - $exception ")
        emitNavigation(LandingPageNavigation.Failure)
    }

    private val _state: MutableStateFlow<LandingPageUiState> =
        MutableStateFlow(LandingPageUiState())
    val state = _state.asStateFlow()

    fun getWordsUzbEng() {
        viewModelScope.launch(handler) {
            repository.getWordUzbEng().let { response ->
                if (response.isSuccessful) {
                    _state.update { uiState ->
                        uiState.copy(
                            remoteUzbEngWords = response.body()!!.words.map { wordItemDtoUz ->
                                wordItemDtoUz.toWordItem()
                            }
                        )
                    }
                } else {
                    emitNavigation(LandingPageNavigation.Failure)
                }
            }
        }
    }

    fun getWordsEngUzb() {
        viewModelScope.launch(handler) {
            repository.getWordsEngUzb().let { response ->
                if (response.isSuccessful) {
                    _state.update { uiState ->
                        uiState.copy(
                            remoteEngUzbWords = response.body()!!.words.map { wordItemDto ->
                                wordItemDto.toWordItem()
                            }
                        )
                    }
                } else {
                    emitNavigation(LandingPageNavigation.Failure)
                }
            }
        }
    }

    fun insertWordsToDB(dao: WordsDao) {
        Log.d("ahi3646", "insertWordsToDB: ")
        val englishWords = state.value.remoteEngUzbWords.map {
            WordModel(
                word = it.word,
                definition = it.definition,
                translations = it.translations,
                audio = it.audio,
                category = it.category,
                language = Constants.LANGUAGE_ENGLISH
            )
        }
        val uzbekWords = state.value.remoteUzbEngWords.map {
            WordModel(
                word = it.word,
                definition = it.definition,
                translations = it.translations,
                audio = it.audio,
                category = it.category,
                language = Constants.LANGUAGE_UZBEK
            )
        }
        val allWords = englishWords
            .toMutableList()
            .apply {
                addAll(uzbekWords)
            }
        viewModelScope.launch(Dispatchers.IO) {
            dao.deleteAllWords()
            dao.upsertWordItem(allWords)
        }
    }

}

@Immutable
data class LandingPageUiState(
    val remoteEngUzbWords: List<WordItem> = listOf(),
    val remoteUzbEngWords: List<WordItem> = listOf(),
)