package com.ierusalem.dictionary.features.landing.db

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.ierusalem.dictionary.features.landing.presentation.model.WordItem
import kotlinx.coroutines.flow.Flow

@Dao
interface WordsDao {
    @Upsert
    suspend fun upsertWordItem(word: WordItem)
    @Query("SELECT*FROM worditem WHERE word LIKE :query ")
    fun getWordsByQuery(query:String): Flow<List<WordItem>>
    @Query("SELECT*FROM worditem WHERE category LIKE :category ")
    fun getWordsByCategory(category:String): List<WordItem>
}