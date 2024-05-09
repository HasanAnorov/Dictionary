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

    fun getAllWords(isEnglish: Boolean){
        viewModelScope.launch(Dispatchers.IO) {
            val language = if (isEnglish) Constants.LANGUAGE_ENGLISH else Constants.LANGUAGE_UZBEK
            val words = dao.getWords(language)
            _state.update {
                it.copy(
                    words = words
                )
            }
        }
        changeCategorySelection("All")
    }

    private fun getWordsByCategory(category: String){
        val isEnglish = state.value.isEnglish
        val language = if (isEnglish) Constants.LANGUAGE_ENGLISH else Constants.LANGUAGE_UZBEK
        viewModelScope.launch(Dispatchers.IO) {
            val words = dao.getWords(language, category)
            _state.update {
                it.copy(
                    words = words
                )
            }
        }
    }

    fun emptyCategories(){
        val categories = listOf(CategoryItem(name = "All", isSelected = true))
        _state.update {
            it.copy(
                categories = categories
            )
        }
    }


    fun getCategories(isEnglish: Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            val language =
                if (isEnglish) Constants.LANGUAGE_ENGLISH else Constants.LANGUAGE_UZBEK
            val categories = state.value.categories
                .toMutableList()
                .apply {
                    addAll(
                        dao.getCategories(language).map { name ->
                            CategoryItem(name = name, isSelected = false)
                        }
                    )
                }
                .distinct()
            _state.update {
                it.copy(
                    categories = categories
                )
            }
        }
    }

    fun openDrawer() {
        _drawerShouldBeOpened.value = true
    }

    fun onChatClicked(category: String){
        viewModelScope.launch(Dispatchers.IO) {
            getWordsByCategory(category)
        }
        changeCategorySelection(category)
    }

    private fun changeCategorySelection(category: String){
        val newCategories = state.value.categories.toMutableList().map {
            if(it.name == category){
                it.copy(isSelected = true)
            }else{
                it.copy(isSelected = false)
            }
        }
        _state.update { uiState ->
            uiState.copy(
                categories = newCategories
            )
        }
    }

    fun resetOpenDrawerAction() {
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

    fun toggleIsSearching(isSearching: Boolean){
        _state.update {
            it.copy(
                isSearching = isSearching
            )
        }
    }

    fun onSearchTextChange(text:String){
        _state.update {
            it.copy(
                searchText = text
            )
        }
    }

    fun insertWordsToDB() {
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
                val application =
                    checkNotNull(extras[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY])
                // Create a SavedStateHandle for this ViewModel from extras
                //val savedStateHandle = extras.createSavedStateHandle()
                return MainViewModel(
                    (application as DictionaryApp).wordsDatabase.wordsDao,
                    application.repositoryImpl,
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
    val words: List<WordModel> = listOf(),
    val isEnglish: Boolean = false,
    val categories: List<CategoryItem> = listOf(CategoryItem(name = "All", isSelected = true)),
    val drawerGestureEnabled: Boolean = false,
    //landing
    val remoteEngUzbWords: List<WordItem> = listOf(),
    val remoteUzbEngWords: List<WordItem> = listOf(),

    //search
    val searchText:String = "",
    val isSearching: Boolean = false
)

data class CategoryItem(
    val name: String,
    val isSelected: Boolean = false
)