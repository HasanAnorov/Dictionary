package com.ierusalem.dictionary.features.landing.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface WordsDao {

    @Query("DELETE FROM wordmodel")
    fun deleteAllWords()

    //to insert all word
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsertWordItem(words: List<WordModel>)
}