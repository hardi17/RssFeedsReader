package com.hardi.skynews.di.module

import com.hardi.skynews.ui.homefeed.HomeFeedAdapter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.scopes.ActivityScoped

@Module
@InstallIn(ActivityComponent::class)
class ActivityModule {

    @ActivityScoped
    @Provides
    fun provideHomeFeed() = HomeFeedAdapter(ArrayList())

}