package com.hardi.skynews.ui.homefeed

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.hardi.skynews.data.model.FeedItems
import com.hardi.skynews.databinding.ActivityHomeFeedBinding
import com.hardi.skynews.utils.UIState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class HomeFeedActivity : AppCompatActivity() {

    @Inject
    lateinit var adapter: HomeFeedAdapter

    private lateinit var viewModel: HomeFeedViewModel


    private lateinit var binding: ActivityHomeFeedBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeFeedBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpViewModel()
        setUpUI()
        setupResponse()
        setupRefreshData()
    }

    private fun setupRefreshData() {
        binding.swipeRefreshLayout.setOnRefreshListener {
            viewModel.refreshData()
            binding.swipeRefreshLayout.isRefreshing = false
        }
    }

    private fun setupResponse() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect {
                    when (it) {
                        is UIState.Success -> {
                            binding.progressBar.visibility = View.GONE
                            binding.erroLayout.visibility = View.GONE
                            renderList(it.data)
                            binding.newsRecyclerview.visibility = View.VISIBLE
                        }

                        is UIState.Loading -> {
                            binding.progressBar.visibility = View.VISIBLE
                            binding.erroLayout.visibility = View.GONE
                            binding.newsRecyclerview.visibility = View.GONE
                        }

                        is UIState.Error -> {
                            //Handle Error
                            binding.newsRecyclerview.visibility = View.GONE
                            binding.progressBar.visibility = View.GONE
                            binding.erroLayout.visibility = View.VISIBLE
                            binding.tvError.text = it.message
                            binding.btnRetry.setOnClickListener {
                                viewModel.refreshData()
                            }
                        }
                    }
                }
            }
        }

    }

    //update recyclerview
    private fun renderList(item: List<FeedItems>) {
        adapter.addData(item)
    }

    //Set recyclerview and divider between item.
    private fun setUpUI() {
        val recyclerView = binding.newsRecyclerview
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.addItemDecoration(
            DividerItemDecoration(
                recyclerView.context,
                (recyclerView.layoutManager as LinearLayoutManager).orientation
            )
        )
        recyclerView.adapter = adapter
    }

    //viewmodel setup
    private fun setUpViewModel() {
        viewModel = ViewModelProvider(this)[HomeFeedViewModel::class.java]
    }


}