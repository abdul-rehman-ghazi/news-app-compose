package com.hotmail.arehmananis.composedemo.android.presentation.news_list

import androidx.compose.runtime.State
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

    private var currentPage = 1
    private val news = mutableListOf<News>()

    init {
        getNews(currentPage)
    }

    private fun getNews(page: Int) {
        viewModelScope.launch {
            getNewsUseCase(page).collect { resource ->
                val uiState = when (resource) {
                    is Resource.Loading -> UiState(isLoading = true)
                    is Resource.Success -> {
                        news.addAll(resource.data!!)
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

    fun loadMore() {
        if (!newsState.value.isLoading &&
            (newsState.value.data?.size?.mod(ApiConstants.REQUEST_PER_PAGE_LENGTH) ?: 0) == 0
        ) {
            currentPage++
            getNews(currentPage)
        }
    }
}