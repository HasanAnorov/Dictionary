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

    //to get words by category
    @Query("SELECT * FROM wordmodel WHERE language=:language AND category=:category")
    fun getWords(language:String,category:String):List<WordModel>

    //to get all words
    @Query("SELECT * FROM wordmodel WHERE language=:language")
    fun getWords(language:String):List<WordModel>

    //to get word by id
    @Query("SELECT * FROM wordmodel WHERE id=:id")
    fun getWord(id:Int):WordModel

    @Query("SELECT * FROM wordmodel WHERE word LIKE '%' || :query || '%'")
    suspend fun searchWords(query: String): List<WordModel>

}