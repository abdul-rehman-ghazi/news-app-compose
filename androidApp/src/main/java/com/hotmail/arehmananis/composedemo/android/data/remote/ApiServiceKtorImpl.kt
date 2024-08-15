package com.hotmail.arehmananis.composedemo.android.data.remote

import com.hotmail.arehmananis.composedemo.android.data.remote.api_service.RoutesHelper
import com.hotmail.arehmananis.composedemo.android.data.remote.dto.response.GetNewsResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class ApiServiceKtorImpl(
    private val routesHelper: RoutesHelper,
    private val httpClient: HttpClient
) : ApiServiceKtor {
    override suspend fun topHeadlines(page: Int): GetNewsResponse {
        return httpClient.get(routesHelper.getUrl("everything?country=us")) {
            url {
                parameters.append("page", page.toString())
            }
        }.body()
    }
}