package com.hotmail.arehmananis.composedemo.android.data.remote.api_service

import android.content.Context
import android.os.Build
import com.hotmail.arehmananis.composedemo.android.common.deviceId
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.api.createClientPlugin
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.DEFAULT
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.accept
import io.ktor.http.ContentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonNamingStrategy
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale

internal object ApiManager {
    var apiConfig: ApiConfig = ApiConfig.DEV

    // Ktor
    @OptIn(ExperimentalSerializationApi::class)
    fun getKtorHttpClient(context: Context/*, authDataSource: AuthDataSource*/) =
        HttpClient(Android) {
            install(HttpTimeout) {
                requestTimeoutMillis = 1000L * 60L
                connectTimeoutMillis = 1000L * 60L
                socketTimeoutMillis = 1000L * 60L
            }

            install(Logging) {
                logger = Logger.DEFAULT
                level = LogLevel.ALL
            }

            install(ContentNegotiation) {
                json(Json {
                    prettyPrint = true
                    isLenient = true
                    ignoreUnknownKeys = true
                    namingStrategy = JsonNamingStrategy.SnakeCase
                })
            }

//        install(CurlLoggingPlugin)
            install(createCustomHeaderPlugin(context))
//        install(createAuthPlugin(authDataSource, apiConfig))

            defaultRequest {
                accept(ContentType.Application.Json)
            }
        }
}

val requestDateTime: String
    get() = LocalDateTime.now()
        .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSSSSS"))

fun createCustomHeaderPlugin(context: Context) = createClientPlugin("CustomHeaderPlugin") {
    onRequest { request, _ ->
        request.headers.apply {
            append(ApiConstants.CONTENT_TYPE, ApiConstants.CONTENT_TYPE_JSON)
            append(ApiConstants.KEY_REQUEST_DATE_TIME, requestDateTime)
            append(ApiConstants.KEY_DEVICE_ID, context.deviceId())
            append(ApiConstants.KEY_LOCALE, Locale.getDefault().language)
            append(ApiConstants.X_DEVICE_NAME, Build.MODEL)
        }
    }
}

