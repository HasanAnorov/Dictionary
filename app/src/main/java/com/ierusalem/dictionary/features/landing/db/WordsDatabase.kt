package com.ierusalem.dictionary.features.landing.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(
    entities = [WordModel::class],
    version = 1
)
@TypeConverters(Converters::class)
abstract class WordsDatabase : RoomDatabase() {
    abstract val wordsDao: WordsDao
}