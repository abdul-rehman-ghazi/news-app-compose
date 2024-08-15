package com.hotmail.arehmananis.composedemo.android.data.remote.dto.response

import kotlinx.serialization.Serializable

@Serializable
open class BaseResponse(
    private var statusInternal: String = "ok",
) {
    val isSuccess
        get() = statusInternal == "ok"
}
