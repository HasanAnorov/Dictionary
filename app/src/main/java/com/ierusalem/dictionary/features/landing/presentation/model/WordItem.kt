package com.ierusalem.dictionary.features.landing.presentation.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class WordItem(
    @PrimaryKey
    val id: Int,
    val word: String,
    val definition: String?,
    val translations: List<String>,
    val audio: String,
    val category: String
)