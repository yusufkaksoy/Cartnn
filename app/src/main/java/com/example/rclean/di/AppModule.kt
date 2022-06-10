package com.example.rclean.di

import com.example.rclean.entities.local.SchoolDatabase
import com.example.rclean.network.api.ApiService
import com.example.rclean.util.Constants
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

}