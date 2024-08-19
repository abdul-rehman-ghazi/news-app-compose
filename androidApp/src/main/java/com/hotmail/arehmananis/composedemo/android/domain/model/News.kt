package com.hotmail.arehmananis.composedemo.android.domain.model

import kotlinx.datetime.Instant
import kotlinx.serialization.Serializable

@Serializable
data class News(
    var author: String?,
    var content: String?,
    var description: String?,
    var publishedAt: Instant,
    var source: String,
    var title: String,
    var url: String,
    var urlToImage: String?,
) {
    val ellipsizeContent: String
        get() {
            return content?.let {
                val startIndex = it.indexOf("[+")
                val endIndex = it.indexOf(" chars]")

                return if (startIndex != -1 && endIndex != -1 && startIndex < endIndex) {
                    it.removeRange(startIndex, endIndex + " chars]".length)
                } else {
                    it
                }
            } ?: kotlin.run { "N/A" }
        }

    companion object {
        fun dummy() = News(
            author = "Armani Syed",
            content = "Javelin thrower Arshad Nadeem of Pakistan made history at the Paris Summer Olympics on Thursday, bagging his home nation its first ever Olympic track and field win. Nadeem will bring home the first g… [+2630 chars]",
            description = "Before Arshad Nadeem’s win, Pakistan hadn't brought home an Olympic gold in four decades.",
            publishedAt = Instant.parse("2024-08-09T12:00:29.000Z"),
            source = "Time",
            title = "Why Arshad Nadeem’s Olympic Gold Medal for Pakistan Is So Significant",
            url = "https://time.com/7009425/arshad-nadeem-olympics-gold-medal-pakistan-significance/",
            urlToImage = "https://api.time.com/wp-content/uploads/2024/08/arshad-nadeem-pakistan.jpg?quality=85&w=1024&h=628&crop=1",
        )
    }
}