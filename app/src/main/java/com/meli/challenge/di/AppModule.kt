package com.meli.challenge.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.meli.challenge.BuildConfig
import com.meli.challenge.api.ApiRepository
import com.meli.challenge.api.SearchService
import com.meli.challenge.data.remote.SearchRemoteDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideRetrofit(gson: Gson) : Retrofit = Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()

    @Provides
    fun provideGson(): Gson = GsonBuilder().create()

    @Provides
    fun provideCharacterService(retrofit: Retrofit): SearchService = retrofit.create(SearchService::class.java)

    @Singleton
    @Provides
    fun provideCharacterRemoteDataSource(characterService: SearchService) = SearchRemoteDataSource(characterService)

    @Singleton
    @Provides
    fun provideRepository(remoteDataSource: SearchRemoteDataSource) =
        ApiRepository(remoteDataSource)
}