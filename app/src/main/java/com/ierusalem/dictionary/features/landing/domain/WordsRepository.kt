package com.ierusalem.dictionary.features.landing.domain

import com.ierusalem.dictionary.features.landing.data.remote.WordsRemote
import retrofit2.Response

interface WordsRepository {
    suspend fun getWordUzbEng(): Response<WordsRemote>
    suspend fun getWordsEngUzb(): Response<WordsRemote>
}