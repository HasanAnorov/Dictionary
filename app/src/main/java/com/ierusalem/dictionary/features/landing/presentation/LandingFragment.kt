package com.ierusalem.dictionary.features.landing.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.ierusalem.dictionary.R
import com.ierusalem.dictionary.theme.DictionaryTheme

class LandingFragment: Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                DictionaryTheme {
                    LandingScreen(
                        onNavigate = { findNavController().navigate(R.id.action_landingFragment_to_homeFragment) }
                    )
                }
            }
        }
    }

}