package com.hardi.skynews.data.api

import com.hardi.skynews.data.model.RssFeed
import retrofit2.http.GET
import javax.inject.Singleton

@Singleton
interface APIService {

    @GET("world.xml")
    suspend fun getHomeFeed(): RssFeed
}