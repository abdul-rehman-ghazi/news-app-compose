package com.hotmail.arehmananis.composedemo.android.presentation.news_list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.twotone.Person
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.hotmail.arehmananis.composedemo.android.common.toRelativeTimeString
import com.hotmail.arehmananis.composedemo.android.domain.model.News

@Composable
fun ItemNews(modifier: Modifier = Modifier, news: News) {
    Card(
        modifier = modifier.fillMaxWidth(),
    ) {
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
//            AsyncImage(
//                model = "https://picsum.photos/seed/picsum/200/300",
//                contentDescription = null
//            )
            Icon(Icons.TwoTone.Person, contentDescription = null, modifier = Modifier.size(96.dp))
        }
    }
}