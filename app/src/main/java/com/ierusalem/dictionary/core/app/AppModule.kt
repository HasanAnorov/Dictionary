package com.ierusalem.dictionary.core.app

import android.content.Context
import android.util.Log
import androidx.room.Room
import com.ierusalem.dictionary.features.landing.data.remote.WordsApi
import com.ierusalem.dictionary.features.landing.data.repository.WordsRepositoryImpl
import com.ierusalem.dictionary.features.landing.db.WordsDao
import com.ierusalem.dictionary.features.landing.db.WordsDatabase
import com.ierusalem.dictionary.features.landing.domain.WordsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideRestClient(@ApplicationContext appContext: Context): RetrofitInstance {
        return RetrofitInstance(appContext)
    }

    @Provides
    fun provideWordsApi(retrofitInstance: RetrofitInstance):WordsApi{
        return retrofitInstance.retrofit.create(WordsApi::class.java)
    }



    @Provides
    @Singleton
    fun provideWordsDao(@ApplicationContext appContext: Context): WordsDao{
        val db = Room.databaseBuilder(appContext,WordsDatabase::class.java,"dictionary_db").build()
        Log.d("ahi3646", "provideWordsDao: $db ")
        return db.wordsDao()
    }

    @Provides
    @Singleton
    fun provideWordsRepository(wordsApi: WordsApi, ): WordsRepository{
        return WordsRepositoryImpl(wordsApi)
    }

}