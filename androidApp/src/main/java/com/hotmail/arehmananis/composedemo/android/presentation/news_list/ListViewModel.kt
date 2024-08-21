package com.hotmail.arehmananis.composedemo.android.presentation.news_list

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hotmail.arehmananis.composedemo.android.common.Resource
import com.hotmail.arehmananis.composedemo.android.data.remote.api_service.ApiConstants
import com.hotmail.arehmananis.composedemo.android.domain.model.News
import com.hotmail.arehmananis.composedemo.android.domain.use_case.get_news.GetNewsUseCase
import com.hotmail.arehmananis.composedemo.android.presentation.UiState
import kotlinx.coroutines.launch

class ListViewModel(
    private val getNewsUseCase: GetNewsUseCase
) : ViewModel() {

    private val _newsState = mutableStateOf(UiState<List<News>>())
    val newsState: State<UiState<List<News>>> = _newsState

    private val _currentPage = mutableIntStateOf(1)
    val currentPage: State<Int> = _currentPage

    val isRefreshing = mutableStateOf(false)

    val news = mutableListOf<News>()

    init {
        getNews(null)
    }

    fun resetPageCount() {
        _currentPage.intValue = 1
    }

    fun getNews(keyword: String? = null, onComplete: (() -> Unit)? = null) {
        viewModelScope.launch {
            getNewsUseCase(_currentPage.intValue, keyword).collect { resource ->
                val uiState = when (resource) {
                    is Resource.Loading -> UiState(isLoading = true)
                    is Resource.Success -> {
                        if (_currentPage.intValue == 1) news.clear()
                        news.addAll(resource.data!!)
                        onComplete?.invoke()
                        UiState(data = news.toList())
                    }

                    is Resource.Error -> UiState(
                        error = resource.message ?: "An unexpected error occurred"
                    )
                }
                _newsState.value = uiState
            }
        }
    }

    fun loadMore(keyword: String? = null) {
        if (!newsState.value.isLoading &&
            (newsState.value.data?.size?.mod(ApiConstants.REQUEST_PER_PAGE_LENGTH) ?: 0) == 0
        ) {
            _currentPage.intValue++
            getNews(keyword)
        }
    }
}