package com.ierusalem.dictionary.features.landing.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class WordModel (
    val word: String,
    val definition: String?,
    val translations: List<String>,
    val audio: String?,
    val category: String,
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0
)