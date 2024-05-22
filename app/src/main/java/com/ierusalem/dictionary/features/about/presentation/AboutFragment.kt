package com.ierusalem.dictionary.features.about.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.findNavController
import com.ierusalem.dictionary.features.home.domain.MainViewModel
import com.ierusalem.dictionary.ui.theme.DictionaryTheme

class AboutFragment : Fragment() {

    private val viewModel: MainViewModel by activityViewModels {
        MainViewModel.Factory
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                DictionaryTheme {

                    val state by viewModel.state.collectAsStateWithLifecycle()
                    AboutScreen(
                        modifier = Modifier.fillMaxSize(),
                        content = state.aboutContent,
                        onNavIconPressed = {
                            findNavController().popBackStack()
                        }
                    )
                }
            }
        }
    }

}