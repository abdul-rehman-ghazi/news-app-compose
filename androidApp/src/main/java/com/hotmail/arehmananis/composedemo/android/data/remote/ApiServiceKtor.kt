package com.hotmail.arehmananis.composedemo.android.data.remote

import com.hotmail.arehmananis.composedemo.android.data.remote.dto.response.GetNewsResponse

interface ApiServiceKtor {
    suspend fun topHeadlines(page: Int): GetNewsResponse

    suspend fun everything(page: Int, keyword: String): GetNewsResponse
}