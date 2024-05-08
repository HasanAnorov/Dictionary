package com.ierusalem.dictionary.features.landing.presentation

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.findNavController
import androidx.room.Room
import com.ierusalem.dictionary.R
import com.ierusalem.dictionary.features.landing.db.WordsDatabase
import com.ierusalem.dictionary.features.landing.domain.LandingViewModel
import com.ierusalem.dictionary.ui.theme.DictionaryTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class LandingFragment: Fragment() {

    private val viewModel: LandingViewModel by viewModels()

    private val englishWordsDb by lazy {
        Room.databaseBuilder(
            requireContext(),
            WordsDatabase::class.java,
            "dictionary.db"
        ).build()
    }

//    private val db by lazy {
//        Room.databaseBuilder(
//            requireContext(),
//            WordsDatabase::class.java,
//            "dictionary.db"
//        ).build()
//    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        viewModel.getWordsUzbEng()
        viewModel.getWordsEngUzb()

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED){
                viewModel.state.collect{
                    if (it.remoteEngUzbWords.isNotEmpty()&&it.remoteUzbEngWords.isNotEmpty()){
                        viewModel.insertWordsToDB(englishWordsDb.wordsDao)
                    }else{
                        Log.d("ahi3646", "data is empty")
                    }
                }
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        return ComposeView(requireContext()).apply {
            setContent {
                DictionaryTheme(darkTheme = true) {
                    LandingScreen(
                        onNavigate = { findNavController().navigate(R.id.action_landingFragment_to_homeFragment) }
                    )
                }
            }
        }
    }

}