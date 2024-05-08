package com.ierusalem.dictionary.features.home.domain

import android.util.Log
import androidx.compose.runtime.Immutable
import androidx.lifecycle.ViewModel
import com.ierusalem.dictionary.core.utils.Constants
import com.ierusalem.dictionary.features.landing.db.WordsDao
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

/**
 * Used to communicate between screens.
 */
class MainViewModel : ViewModel() {

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

}

@Immutable
data class HomeScreenState(
    val words: List<String> = Constants.PREVIEW_WORDS_DATA,
    val isEnglish: Boolean = false
)