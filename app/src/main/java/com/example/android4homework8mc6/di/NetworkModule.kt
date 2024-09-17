package com.example.android4homework8mc6.di

import com.example.android4homework8mc6.data.remote.RetrofitClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideRetrofitClient() = RetrofitClient()

    @Singleton
    @Provides
    fun provideAnimeApiService() = RetrofitClient().provideAnimeApiService()

    @Singleton
    @Provides
    fun provideMangaApiService() = RetrofitClient().provideMangaApiService()
}