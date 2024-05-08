package com.ierusalem.dictionary.features.landing.data.model_uz


import com.google.gson.annotations.SerializedName
import com.ierusalem.dictionary.features.landing.presentation.model.WordItem

data class WordItemDtoUz(
    @SerializedName("created")
    val created: String,
    @SerializedName("direction")
    val direction: Direction,
    @SerializedName("enWords")
    val enWords: List<String>,
    @SerializedName("id")
    val id: Int,
    @SerializedName("updated")
    val updated: String,
    @SerializedName("word")
    val word: String
){
    fun toWordItem(): WordItem {
        return WordItem(
            id = id,
            word = word,
            definition = null,
            translations = enWords,
            category = direction.name,
            audio = null
        )
    }
}