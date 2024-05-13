package com.ierusalem.dictionary.core.app

import android.app.Application
import androidx.room.Room
import com.ierusalem.dictionary.features.downloader.AndroidDownloader
import com.ierusalem.dictionary.features.landing.data.remote.WordsApi
import com.ierusalem.dictionary.features.landing.data.repository.WordsRepositoryImpl
import com.ierusalem.dictionary.features.landing.db.WordsDatabase

class DictionaryApp: Application(){

    lateinit var repositoryImpl: WordsRepositoryImpl
    lateinit var wordsDatabase: WordsDatabase
    lateinit var downloader:AndroidDownloader
    override fun onCreate() {
        super.onCreate()
        downloader = AndroidDownloader(this)
        wordsDatabase = Room.databaseBuilder(applicationContext, WordsDatabase::class.java, "dictionary_db").build()
        repositoryImpl = WordsRepositoryImpl(RetrofitInstance(applicationContext).retrofit.create(WordsApi::class.java))
    }
}