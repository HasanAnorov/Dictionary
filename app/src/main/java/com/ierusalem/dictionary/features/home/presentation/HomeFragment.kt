package com.ierusalem.dictionary.features.home.presentation

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.unit.Constraints
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ierusalem.dictionary.core.utils.Constants
import com.ierusalem.dictionary.features.home.domain.MainViewModel
import com.ierusalem.dictionary.ui.theme.DictionaryTheme

class HomeFragment : Fragment() {

    private val viewModel: MainViewModel by activityViewModels()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        val isEnglish = arguments?.getBoolean(Constants.IS_ENGLISH_BUNDLE_KEY) ?: false
        viewModel.toggleEnglish(isEnglish)
        Toast.makeText(context, isEnglish.toString(), Toast.LENGTH_SHORT).show()
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
            DictionaryTheme {
                HomeContent(
                    uiState = uiState,
                    onNavIconPressed = {
                        viewModel.openDrawer()
                    }
                )
            }

        }
    }

}