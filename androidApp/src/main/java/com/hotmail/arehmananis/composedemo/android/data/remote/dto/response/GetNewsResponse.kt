package com.hotmail.arehmananis.composedemo.android.data.remote.dto.response

import com.hotmail.arehmananis.composedemo.android.domain.model.News
import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import kotlinx.serialization.Serializable

@Serializable
data class GetNewsResponse(
    var status: String,
    var code: String?,
    var articles: List<Article>?,
    var totalResults: Int?,
    var message: String?
) // : BaseResponse(status)

@Serializable
data class Article(
    var author: String?,
    var content: String?,
    var description: String?,
    var publishedAt: String?,
    var source: Source,
    var title: String,
    var url: String,
    var urlToImage: String?
)

@Serializable
data class Source(
    var id: String?,
    var name: String
)

fun Article.toNews(): News = News(
    author = author,
    content = content,
    description = description,
    publishedAt = if (publishedAt != null) Instant.parse(publishedAt!!) else Clock.System.now(),
    source = source.name,
    title = title,
    url = url,
    urlToImage = urlToImage
)