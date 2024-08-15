package com.hotmail.arehmananis.composedemo.android.data.remote

import com.hotmail.arehmananis.composedemo.android.data.remote.api_service.ApiConstants
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
        return httpClient.get(routesHelper.getUrl("top-headlines?country=us")) {
            url {
                parameters.apply {
                    append("page", page.toString())
                    append("pageSize", ApiConstants.REQUEST_PER_PAGE_LENGTH.toString())
                }
            }
        }.body()
    }

    override suspend fun everything(page: Int, keyword: String): GetNewsResponse {
        return httpClient.get(routesHelper.getUrl("everything")) {
            url {
                parameters.apply {
                    append("q", keyword)
                    append("page", page.toString())
                    append("pageSize", ApiConstants.REQUEST_PER_PAGE_LENGTH.toString())
//                    append(
//                        "from",
//                        LocalDateTime.now().minusYears(1)
//                            .format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS"))
//                    )
                }
            }
        }.body()
    }
}