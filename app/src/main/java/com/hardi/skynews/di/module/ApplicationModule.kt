package com.hardi.skynews.di.module

import com.hardi.skynews.data.api.APIService
import com.hardi.skynews.di.BaseUrl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.simplexml.SimpleXmlConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ApplicationModule {

    @BaseUrl
    @Provides
    fun provideBaseUrl(): String =
        "http://feeds.skynews.com/feeds/rss/"

    @Provides
    @Singleton
    fun provideNetworkService(
        @BaseUrl baseUrl: String
    ): APIService {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(SimpleXmlConverterFactory.create())
            .build()
            .create(APIService::class.java)
    }

}