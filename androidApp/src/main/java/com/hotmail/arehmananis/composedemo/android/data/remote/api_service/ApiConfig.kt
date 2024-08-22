package com.hotmail.arehmananis.composedemo.android.data.remote.api_service

enum class ApiConfig(val baseUrl: String) {
    DEV(baseUrl = "https://newsapi.org/v2/"),
    QA(baseUrl = "https://newsapi.org/v2/"),
    PROD(baseUrl = "https://newsapi.org/v2/");
}