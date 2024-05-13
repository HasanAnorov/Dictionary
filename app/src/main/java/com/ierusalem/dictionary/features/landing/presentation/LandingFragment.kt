package com.ierusalem.dictionary.features.landing.presentation

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.findNavController
import com.ierusalem.dictionary.R
import com.ierusalem.dictionary.core.utils.Constants
import com.ierusalem.dictionary.features.home.domain.MainViewModel
import com.ierusalem.dictionary.ui.theme.DictionaryTheme
import kotlinx.coroutines.launch

class LandingFragment: Fragment() {

    private val viewModel: MainViewModel by activityViewModels{
        MainViewModel.Factory
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        viewModel.getWordsUzbEng()
        viewModel.getWordsEngUzb()

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED){
                viewModel.state.collect{
                    if (it.remoteEngUzbWords.isNotEmpty()&&it.remoteUzbEngWords.isNotEmpty()){
                        viewModel.insertWordsToDB()
                    }else{
                        Log.d("ahi3646", "data is empty")
                    }
                }
            }
        }
    }

    override fun onStart() {
        super.onStart()
        viewModel.toggleDrawerGestureEnabled(false)
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
                        onNavigate = {isEnglish ->
                            val bundle = bundleOf(Constants.IS_ENGLISH_BUNDLE_KEY to isEnglish)
                            findNavController().navigate(R.id.action_landingFragment_to_homeFragment, bundle)
                        }
                    )
                }
            }
        }
    }

}