package com.hotmail.arehmananis.composedemo.android.data.remote

import com.hotmail.arehmananis.composedemo.android.data.remote.dto.GetNewsResponse

interface ApiServiceKtor {
    suspend fun topHeadlines(
        page: Int
    ): GetNewsResponse
}