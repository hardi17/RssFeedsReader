package com.hardi.skynews.data.repository


import com.hardi.skynews.data.api.APIService
import com.hardi.skynews.data.model.FeedItems
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class HomeFeedRepository @Inject constructor(private val apiService: APIService) {

    fun getHomeFeed(): Flow<List<FeedItems>> {
        return flow {
            val items = apiService.getHomeFeed().feedList
            items?.let { emit(it) }
        }
    }

}