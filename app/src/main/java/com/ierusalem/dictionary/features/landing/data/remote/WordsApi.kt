package com.ierusalem.dictionary.features.landing.data.remote

import com.google.gson.annotations.SerializedName
import com.ierusalem.dictionary.features.about.data.response_model.AboutResponse
import com.ierusalem.dictionary.features.landing.data.model.WordItemDto
import com.ierusalem.dictionary.features.landing.data.model_uz.WordItemDtoUz
import retrofit2.Response
import retrofit2.http.GET

interface WordsApi {
    @GET("en-uz")
    suspend fun getEngUzb(): Response<WordsRemote>

    //use different response model
    @GET("uz-en")
    suspend fun getUzbEng(): Response<WordsRemoteUz>

    @GET("about")
    suspend fun getAboutContent(): Response<AboutResponse>
}

data class WordsRemote(
    @SerializedName("status")
    val status: Int,
    @SerializedName("data")
    val words: List<WordItemDto>
)

data class WordsRemoteUz(
    @SerializedName("status")
    val status: Int,
    @SerializedName("data")
    val words: List<WordItemDtoUz>
)