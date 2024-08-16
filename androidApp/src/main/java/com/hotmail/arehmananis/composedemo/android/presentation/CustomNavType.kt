package com.hotmail.arehmananis.composedemo.android.presentation

import android.net.Uri
import android.os.Bundle
import androidx.navigation.NavType
import com.hotmail.arehmananis.composedemo.android.domain.model.News
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

object CustomNavType {
    val NewsType = object : NavType<News>(isNullableAllowed = false) {
        override fun get(bundle: Bundle, key: String): News? {
            return Json.decodeFromString(bundle.getString(key) ?: return null)
        }

        override fun parseValue(value: String): News {
            return Json.decodeFromString(Uri.decode(value))
        }

        override fun serializeAsValue(value: News): String {
            return Uri.encode(Json.encodeToString(value))
        }

        override fun put(bundle: Bundle, key: String, value: News) {
            bundle.putString(key, Json.encodeToString(value))
        }

    }
}