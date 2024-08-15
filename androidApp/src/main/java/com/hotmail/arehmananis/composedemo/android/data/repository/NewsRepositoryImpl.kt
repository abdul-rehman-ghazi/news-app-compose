package com.hotmail.arehmananis.composedemo.android.data.repository

import com.hotmail.arehmananis.composedemo.android.data.remote.ApiServiceKtor
import com.hotmail.arehmananis.composedemo.android.data.remote.dto.response.GetNewsResponse
import com.hotmail.arehmananis.composedemo.android.domain.repository.NewsRepository

class NewsRepositoryImpl(private val apiServiceKtor: ApiServiceKtor) : NewsRepository {
    override suspend fun getTopHeadline(page: Int): GetNewsResponse {
        return apiServiceKtor.topHeadlines(page)
    }

    override suspend fun getEverything(page: Int, keyword: String): GetNewsResponse {
        return apiServiceKtor.everything(page, keyword)
    }
}