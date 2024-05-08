package com.ierusalem.dictionary.core.app

import android.app.Application
import androidx.room.Room
import com.ierusalem.dictionary.features.landing.data.remote.WordsApi
import com.ierusalem.dictionary.features.landing.data.repository.WordsRepositoryImpl
import com.ierusalem.dictionary.features.landing.db.WordsDatabase

class DictionaryApp: Application(){

    lateinit var repositoryImpl: WordsRepositoryImpl
    lateinit var wordsDatabase: WordsDatabase
    override fun onCreate() {
        super.onCreate()
        wordsDatabase = Room.databaseBuilder(applicationContext, WordsDatabase::class.java, "dictionary_db").build()
        repositoryImpl = WordsRepositoryImpl(RetrofitInstance(applicationContext).retrofit.create(WordsApi::class.java))
    }
}