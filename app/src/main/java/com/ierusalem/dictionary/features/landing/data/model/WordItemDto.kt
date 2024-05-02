package com.ierusalem.dictionary.features.landing.data.model


import com.google.gson.annotations.SerializedName
import com.ierusalem.dictionary.features.landing.presentation.model.WordItem

data class WordItemDto(
    @SerializedName("audio")
    val audio: String,
    @SerializedName("created")
    val created: String,
    @SerializedName("definition")
    val definition: String?,
    @SerializedName("direction")
    val direction: Direction,
    @SerializedName("id")
    val id: Int,
    @SerializedName("updated")
    val updated: String,
    @SerializedName("uzWords")
    val uzWords: List<String>,
    @SerializedName("word")
    val word: String
){
    fun toWordItem(): WordItem {
        return WordItem(
            word = word,
            definition = definition,
            translations = uzWords,
            category = direction.name,
            audio = audio
        )
    }
}