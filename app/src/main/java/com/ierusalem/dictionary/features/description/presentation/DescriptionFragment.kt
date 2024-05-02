package com.ierusalem.dictionary.features.description.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import com.ierusalem.dictionary.theme.DictionaryTheme

class DescriptionFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                DictionaryTheme {
                    DescriptionScreen()
                }
            }
        }
    }

}