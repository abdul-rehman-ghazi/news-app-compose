package com.hotmail.arehmananis.composedemo.android.domain.model

import java.util.Date

class News(
    var author: String,
    var content: String?,
    var description: String?,
    var publishedAt: Date,
    var source: String,
    var title: String,
    var url: String,
    var urlToImage: String?,
)