package com.hotmail.arehmananis.composedemo.android.presentation.news_list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.hotmail.arehmananis.composedemo.android.common.toRelativeTimeString
import com.hotmail.arehmananis.composedemo.android.domain.model.News
import kotlinx.datetime.Instant

@Composable
fun ItemNews(modifier: Modifier = Modifier, news: News, onItemClick: () -> Unit) {
    Card(modifier = modifier
        .fillMaxWidth()
        .clickable { onItemClick() }) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .height(IntrinsicSize.Min),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier
                    .weight(1F)
                    .padding(end = 8.dp)
                    .fillMaxHeight(),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = news.title,
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    text = news.publishedAt.toRelativeTimeString(),
                    style = MaterialTheme.typography.labelMedium
                )
            }
            if (news.urlToImage != null) {
                AsyncImage(
                    modifier = Modifier.size(width = 120.dp, height = 80.dp),
                    model = news.urlToImage,
                    contentDescription = null,
                )
            }
        }
    }
}

@Preview
@Composable
fun ItemNewsPreview(modifier: Modifier = Modifier) {
    ItemNews(
        news = News.dummy(),
        onItemClick = { }
    )
}