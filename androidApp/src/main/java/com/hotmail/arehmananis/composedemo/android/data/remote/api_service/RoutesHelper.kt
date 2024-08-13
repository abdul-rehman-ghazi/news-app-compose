package com.hotmail.arehmananis.composedemo.android.data.remote.api_service


class RoutesHelper(private val apiConfig: ApiConfig) {

    fun getUrl(endPoint: String): String {
        return "${apiConfig.baseUrl}$endPoint"
    }
}