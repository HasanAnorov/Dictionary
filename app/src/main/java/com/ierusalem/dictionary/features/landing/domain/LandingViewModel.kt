package com.ierusalem.dictionary.features.landing.domain

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LandingViewModel @Inject constructor(
    private val repository: WordsRepository
): ViewModel() {

    private val handler = CoroutineExceptionHandler { _, exception ->
        Log.d("ahi3646", " coroutineExceptionHandler : error - $exception ")
        //emitNavigation(HomeScreenNavigation.InvalidResponse)
    }

    fun getWordsUzbEng(){

    }

    fun getWordsEngUzb(){
        viewModelScope.launch(handler) {
            repository.getWordsEngUzb().let { response ->
                if(response.isSuccessful){
                    Log.d("ahi3646", "success: ${response.body()} ")
                }else{
                    Log.d("ahi3646", "failure: ${response.errorBody()} ")
                }
            }
        }
    }

}