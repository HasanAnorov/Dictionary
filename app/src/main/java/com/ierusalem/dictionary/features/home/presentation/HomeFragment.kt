package com.ierusalem.dictionary.features.home.presentation

import android.content.Context
import android.media.AudioAttributes
import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.ComposeView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.findNavController
import com.ierusalem.dictionary.R
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

    override fun onStart() {
        super.onStart()
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
                if (uiState.isSearching) {
                    viewModel.toggleIsSearching(false)
                } else {
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
                        val bundle = bundleOf(Constants.WORD_ID to it)
                        findNavController().navigate(
                            R.id.action_homeFragment_to_descriptionFragment,
                            bundle
                        )
                    },
                    onVoiceClick = {
                                   playAudio(it)
                    },
                    onSearchTextChange = {
                        viewModel.onSearchTextChange(it)
                    }
                )
            }
        }
    }

    private fun playAudio(url: String) {
        val audioPath = "${Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)}/" + url
        Log.d("ahi3646", "playAudio: $audioPath ")
        val myUri: Uri = Uri.parse(audioPath) // initialize Uri here
        try {
            MediaPlayer().apply {
                setAudioAttributes(
                    AudioAttributes.Builder()
                        .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                        .setUsage(AudioAttributes.USAGE_MEDIA)
                        .build()
                )
                setDataSource(requireContext(), myUri)
                prepare()
                start()
            }
        }catch (e:Exception){
            Toast.makeText(requireContext(), "Can't play audio, invalid audio file!", Toast.LENGTH_SHORT).show()
        }
    }

}