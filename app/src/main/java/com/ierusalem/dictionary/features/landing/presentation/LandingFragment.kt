package com.ierusalem.dictionary.features.landing.presentation

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.ierusalem.dictionary.R
import com.ierusalem.dictionary.features.landing.domain.LandingViewModel
import com.ierusalem.dictionary.theme.DictionaryTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LandingFragment: Fragment() {

    private val viewModel: LandingViewModel by viewModels()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        viewModel.getWordsEngUzb()
        viewModel.getWordsUzbEng()
        //viewModel.insertWordsToDB()
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