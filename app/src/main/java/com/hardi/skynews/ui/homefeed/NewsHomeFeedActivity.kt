package com.hardi.skynews.ui.homefeed

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.hardi.skynews.R
import com.hardi.skynews.databinding.ActivityHomeFeedBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewsHomeFeedActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeFeedBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeFeedBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}