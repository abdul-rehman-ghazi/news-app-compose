package com.hotmail.arehmananis.composedemo.android.data.remote.dto

data class GetNewsResponse(
    var articles: List<Article>,
    var status: String,
    var totalResults: Int
) {
    data class Article(
        var author: String,
        var content: Any,
        var description: Any,
        var publishedAt: String,
        var source: Source,
        var title: String,
        var url: String,
        var urlToImage: Any
    ) {
        data class Source(
            var id: String,
            var name: String
        )
    }
}