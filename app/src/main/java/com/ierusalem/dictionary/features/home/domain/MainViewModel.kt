package com.ierusalem.dictionary.features.home.domain

import android.util.Log
import androidx.compose.runtime.Immutable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import com.ierusalem.androchat.ui.navigation.DefaultNavigationEventDelegate
import com.ierusalem.androchat.ui.navigation.NavigationEventDelegate
import com.ierusalem.androchat.ui.navigation.emitNavigation
import com.ierusalem.dictionary.core.app.DictionaryApp
import com.ierusalem.dictionary.core.utils.Constants
import com.ierusalem.dictionary.features.landing.db.WordModel
import com.ierusalem.dictionary.features.landing.db.WordsDao
import com.ierusalem.dictionary.features.landing.domain.WordsRepository
import com.ierusalem.dictionary.features.landing.presentation.LandingPageNavigation
import com.ierusalem.dictionary.features.landing.presentation.model.WordItem
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

/**
 * Used to communicate between screens.
 */
class MainViewModel(
    private val dao: WordsDao,
    private val repository: WordsRepository
) : ViewModel(),
    NavigationEventDelegate<LandingPageNavigation> by DefaultNavigationEventDelegate() {

    private val handler = CoroutineExceptionHandler { _, exception ->
        Log.d("ahi3646", " coroutineExceptionHandler : error - $exception ")
        emitNavigation(LandingPageNavigation.Failure)
    }

    private val _drawerShouldBeOpened = MutableStateFlow(false)
    val drawerShouldBeOpened = _drawerShouldBeOpened.asStateFlow()


    private val _state: MutableStateFlow<HomeScreenState> = MutableStateFlow(HomeScreenState())
    val state = _state.asStateFlow()


    fun openDrawer() {
        Log.d("ahi3646", "openDrawer: ")
        _drawerShouldBeOpened.value = true
    }

    fun resetOpenDrawerAction() {
        Log.d("ahi3646", "resetOpenDrawerAction: ")
        _drawerShouldBeOpened.value = false
    }

    fun toggleEnglish(isEnglish: Boolean) {
        _state.update {
            it.copy(
                isEnglish = isEnglish
            )
        }
    }

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

    fun insertWordsToDB() {
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

    // Define ViewModel factory in a companion object
    companion object {
        val Factory: ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(
                modelClass: Class<T>,
                extras: CreationExtras
            ): T {
                // Get the Application object from extras
                val application = checkNotNull(extras[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY])
                // Create a SavedStateHandle for this ViewModel from extras
                //val savedStateHandle = extras.createSavedStateHandle()
                return MainViewModel(
                    (application as DictionaryApp).wordsDatabase.wordsDao,
                    (application as DictionaryApp).repositoryImpl,
                    //savedStateHandle
                ) as T
            }
        }
    }

    fun toggleDrawerGestureEnabled(enabled: Boolean) {
        _state.update {
            it.copy(
                drawerGestureEnabled = enabled
            )
        }
    }

}

@Immutable
data class HomeScreenState(
    val words: List<String> = Constants.PREVIEW_WORDS_DATA,
    val isEnglish: Boolean = false,

    val drawerGestureEnabled: Boolean = false,

    //landing
    val remoteEngUzbWords: List<WordItem> = listOf(),
    val remoteUzbEngWords: List<WordItem> = listOf(),
)