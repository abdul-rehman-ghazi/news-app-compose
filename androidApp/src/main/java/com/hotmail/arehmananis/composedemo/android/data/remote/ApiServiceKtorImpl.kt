package com.hotmail.arehmananis.composedemo.android.data.remote

import com.hotmail.arehmananis.composedemo.android.data.remote.dto.GetNewsResponse

class ApiServiceKtorImpl() : ApiServiceKtor {
    override suspend fun topHeadlines(page: Int): GetNewsResponse {
        TODO("Not yet implemented")
    }
}