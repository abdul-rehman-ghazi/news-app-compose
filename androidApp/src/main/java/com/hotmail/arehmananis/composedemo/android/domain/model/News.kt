package com.hotmail.arehmananis.composedemo.android.domain.model

import kotlinx.datetime.Instant
import kotlinx.serialization.Serializable
import java.util.Date

@Serializable
class News(
    var author: String?,
    var content: String?,
    var description: String?,
    var publishedAt: Instant,
    var source: String,
    var title: String,
    var url: String,
    var urlToImage: String?,
)