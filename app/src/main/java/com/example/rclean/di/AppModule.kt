package com.example.rclean.di

import android.content.Context
import com.example.rclean.entities.local.CharactersDao
import com.example.rclean.entities.local.CharactersDatabase
import com.example.rclean.entities.local.SchoolDatabase
import com.example.rclean.network.api.ApiService
import com.example.rclean.util.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ActivityContext
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun injectDao(database: SchoolDatabase) = database.schoolDao


    @Provides
    fun provideBaseUrl() = Constants.BASE_URL


    @Provides
    @Singleton
    fun provideRetrofitInstance(BASE_URL: String): ApiService =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).build()
            .create(ApiService::class.java)

    @Singleton
    @Provides
    fun provideCharactersDatabase(@ApplicationContext context: Context) : CharactersDatabase{
        return CharactersDatabase.getInstance(context)

    }

    @Singleton
    @Provides
    fun provideCharactersDao(charactersDatabase:CharactersDatabase) :CharactersDao {
        return charactersDatabase.getCharactersDao()
    }


}