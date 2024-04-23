package com.ierusalem.dictionary.features.landing.data.repository

import com.ierusalem.dictionary.features.landing.data.remote.WordsApi
import com.ierusalem.dictionary.features.landing.data.remote.WordsRemote
import com.ierusalem.dictionary.features.landing.domain.WordsRepository
import retrofit2.Response

class WordsRepositoryImpl(
    private val wordsApi: WordsApi
) : WordsRepository {
    override suspend fun getWordUzbEng(): Response<WordsRemote> = wordsApi.getUzbEng()

    override suspend fun getWordsEngUzb(): Response<WordsRemote> = wordsApi.getEngUzb()
}