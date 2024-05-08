package com.ierusalem.dictionary.features.landing.domain

import com.ierusalem.dictionary.features.landing.data.remote.WordsRemote
import com.ierusalem.dictionary.features.landing.data.remote.WordsRemoteUz
import retrofit2.Response

interface WordsRepository {
    suspend fun getWordUzbEng(): Response<WordsRemoteUz>
    suspend fun getWordsEngUzb(): Response<WordsRemote>
}