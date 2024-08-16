package com.hotmail.arehmananis.composedemo.android.domain.model

import com.hotmail.arehmananis.composedemo.android.common.DateSerializer
import kotlinx.serialization.Serializable
import java.util.Date

@Serializable
class News(
    var author: String?,
    var content: String?,
    var description: String?,
    @Serializable(with = DateSerializer::class) var publishedAt: Date,
    var source: String,
    var title: String,
    var url: String,
    var urlToImage: String?,
)