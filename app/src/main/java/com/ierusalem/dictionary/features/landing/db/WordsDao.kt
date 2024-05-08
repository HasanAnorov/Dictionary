package com.ierusalem.dictionary.features.landing.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface WordsDao {

    //empty db
    @Query("DELETE FROM wordmodel")
    fun deleteAllWords()

    //to insert all word
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsertWordItem(words: List<WordModel>)

    //to get categories
    @Query("SELECT category FROM wordmodel WHERE language=:language GROUP BY category")
    fun getCategories(language:String):List<String>
}