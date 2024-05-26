package com.hardi.skynews.di.module

import android.content.Context
import com.hardi.skynews.data.api.APIService
import com.hardi.skynews.di.BaseUrl
import com.hardi.skynews.utils.InternetConnected
import com.hardi.skynews.utils.NetworkHelper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
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

    @Provides
    @Singleton
    fun provideNetworkHelper(@ApplicationContext context: Context): NetworkHelper {
        return InternetConnected(context)
    }

}