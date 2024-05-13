package com.ierusalem.dictionary.features.description.presentation

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.ierusalem.dictionary.core.utils.Constants
import com.ierusalem.dictionary.features.home.domain.MainViewModel
import com.ierusalem.dictionary.ui.theme.DictionaryTheme

class DescriptionFragment : Fragment() {

    private val viewModel: MainViewModel by activityViewModels {
        MainViewModel.Factory
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        val wordId = arguments?.getInt(Constants.WORD_ID) ?: -1
        if (wordId>0){
            viewModel.getWord(wordId)
        }else{
            Toast.makeText(requireContext(), "Cannot find a word!", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onStart() {
        super.onStart()
        viewModel.toggleDrawerGestureEnabled(false)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        viewModel.toggleDrawerGestureEnabled(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                val state by viewModel.state.collectAsState()
                DictionaryTheme {
                    DescriptionScreen(
                        wordModel = state.word
                    )
                }
            }
        }
    }

}