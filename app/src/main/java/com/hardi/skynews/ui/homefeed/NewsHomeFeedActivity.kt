package com.hardi.skynews.ui.homefeed

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.hardi.skynews.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewsHomeFeedActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }
}