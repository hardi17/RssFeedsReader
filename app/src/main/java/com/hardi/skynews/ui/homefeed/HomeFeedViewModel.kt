package com.hardi.skynews.ui.homefeed

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hardi.skynews.data.model.FeedItems
import com.hardi.skynews.data.repository.HomeFeedRepository
import com.hardi.skynews.utils.NetworkHelper
import com.hardi.skynews.utils.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeFeedViewModel @Inject constructor(
    private val homeFeedRepository: HomeFeedRepository,
    private val networkHelper: NetworkHelper
) : ViewModel() {

    private val _uiState = MutableStateFlow<UIState<List<FeedItems>>>(UIState.Loading)

    val uiState: StateFlow<UIState<List<FeedItems>>> = _uiState

    init {
        fetchHomeNews()
    }

    //Get news feed from repository and update state value using sflow
    private fun fetchHomeNews() {
        if (networkHelper.isInternetConnected()) {
            viewModelScope.launch(Dispatchers.Main) {
                homeFeedRepository.getHomeFeed()
                    .flowOn(Dispatchers.IO)
                    .catch { e ->
                        _uiState.value = UIState.Error(e.toString())
                    }.collect {
                        _uiState.value = UIState.Success(it)
                    }
            }
        }else{
            _uiState.value = UIState.Error("No internet connection")
        }
    }

    fun refreshData() {
        fetchHomeNews()
    }
}