package com.ierusalem.dictionary.features.home.presentation

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.compose.BackHandler
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.findNavController
import com.ierusalem.dictionary.core.utils.Constants
import com.ierusalem.dictionary.features.home.domain.MainViewModel
import com.ierusalem.dictionary.ui.theme.DictionaryTheme

class HomeFragment : Fragment() {

    private val viewModel: MainViewModel by activityViewModels {
        MainViewModel.Factory
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        val isEnglish = arguments?.getBoolean(Constants.IS_ENGLISH_BUNDLE_KEY) ?: false
        viewModel.toggleEnglish(isEnglish)
        viewModel.getAllWords(isEnglish)
        viewModel.getCategories(isEnglish)
        viewModel.toggleDrawerGestureEnabled(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = ComposeView(requireContext()).apply {
        layoutParams = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )
        setContent {
            val uiState by viewModel.state.collectAsStateWithLifecycle()
            BackHandler {
                if(uiState.isSearching){
                    viewModel.toggleIsSearching(false)
                }else{
                    viewModel.emptyCategories()
                    findNavController().popBackStack()
                }
            }
            DictionaryTheme {
                HomeContent(
                    uiState = uiState,
                    onNavIconPressed = {
                        viewModel.openDrawer()
                    },
                    onSearchClick = {
                        viewModel.toggleIsSearching(true)
                    },
                    onItemClick = {

                    },
                    onVoiceClick = {

                    },
                    onSearchTextChange = {
                        viewModel.onSearchTextChange(it)
                    }
                )
            }

        }
    }

}