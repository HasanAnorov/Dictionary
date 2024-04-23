package com.ierusalem.dictionary.features.landing.presentation.model

data class WordItem(
    val word: String,
    val definition: String?,
    val translations: List<String>,
    val audio: String,
    val category: String
)