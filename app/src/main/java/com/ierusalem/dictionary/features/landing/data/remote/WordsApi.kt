package com.ierusalem.dictionary.features.landing.data.remote

import com.google.gson.annotations.SerializedName
import com.ierusalem.dictionary.features.landing.data.model.WordItemDto
import retrofit2.Response
import retrofit2.http.GET

interface WordsApi {
    @GET("en-uz")
    suspend fun getEngUzb(): Response<WordsRemote>

    //user different response model
    @GET("uz-en")
    suspend fun getUzbEng(): Response<WordsRemote>
}

data class WordsRemote(
    @SerializedName("status")
    val status: Int,
    @SerializedName("data")
    val words: List<WordItemDto>
)