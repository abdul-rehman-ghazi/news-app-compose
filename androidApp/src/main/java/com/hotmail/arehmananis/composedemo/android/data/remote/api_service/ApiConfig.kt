package com.hotmail.arehmananis.composedemo.android.data.remote.api_service

enum class ApiConfig(val baseUrl: String, val apiKey: String) {
    DEV(baseUrl = "https://newsapi.org/v2/", "83a66974ac094cac985839863ce3400c"),
    QA(baseUrl = "https://newsapi.org/v2/", "83a66974ac094cac985839863ce3400c"),
    PROD(baseUrl = "https://newsapi.org/v2/", "83a66974ac094cac985839863ce3400c");
}