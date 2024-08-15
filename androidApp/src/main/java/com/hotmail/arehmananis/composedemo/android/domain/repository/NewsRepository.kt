package com.hotmail.arehmananis.composedemo.android.domain.repository

import com.hotmail.arehmananis.composedemo.android.data.remote.dto.response.GetNewsResponse

interface NewsRepository {
    suspend fun getTopHeadline(page: Int): GetNewsResponse

    suspend fun getEverything(page: Int, keyword: String): GetNewsResponse
}