package com.ierusalem.dictionary.app

import android.content.Context
import com.ierusalem.dictionary.features.landing.data.remote.WordsApi
import com.ierusalem.dictionary.features.landing.data.repository.WordsRepositoryImpl
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
    fun provideWordsRepository(wordsApi: WordsApi, ): WordsRepository{
        return WordsRepositoryImpl(wordsApi)
    }

}