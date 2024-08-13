package com.hotmail.arehmananis.composedemo.android.data.remote.dto.response

import com.hotmail.arehmananis.composedemo.android.common.toDate
import com.hotmail.arehmananis.composedemo.android.domain.model.News

data class GetNewsResponse(
    var articles: List<Article>,
    var status: String,
    var totalResults: Int
)

data class Article(
    var author: String,
    var content: String?,
    var description: String?,
    var publishedAt: String,
    var source: Source,
    var title: String,
    var url: String,
    var urlToImage: String?
)

data class Source(
    var id: String,
    var name: String
)

fun Article.toNews(): News = News(
    author = author,
    content = content,
    description = description,
    publishedAt = publishedAt.toDate("yyyy-MM-dd'T'HH:mm:ssX")!!,
    source = source.name,
    title = title,
    url = url,
    urlToImage = urlToImage
)