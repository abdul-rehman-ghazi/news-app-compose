package com.hotmail.arehmananis.composedemo.android.presentation.news_details

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.LinkAnnotation
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextLinkStyles
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withLink
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import com.hotmail.arehmananis.composedemo.android.domain.model.News
import com.hotmail.arehmananis.composedemo.android.presentation.launchChromeTab
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsScreen(
    news: News = News.dummy(),
    navigateBack: () -> Unit = {},
    viewModel: DetailsViewModel = koinViewModel { parametersOf(News.dummy()) }
) {
    val context = LocalContext.current

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        news.title,
                        maxLines = 1,
                        style = MaterialTheme.typography.titleLarge,
                        overflow = TextOverflow.Ellipsis
                    )
                },
                navigationIcon = {
                    IconButton(onClick = navigateBack) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = null
                        )
                    }
                },
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp),
        ) {
            Text(
                text = news.title,
                style = MaterialTheme.typography.titleMedium
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                buildAnnotatedString {
                    withStyle(style = SpanStyle(color = MaterialTheme.colorScheme.secondary)) {
                        append(news.ellipsizeContent)
                    }
                    val link = LinkAnnotation.Url(
                        news.url,
                        TextLinkStyles(SpanStyle(color = MaterialTheme.colorScheme.primary))
                    ) { launchChromeTab(context, news.url) }
                    withLink(link) { append("See More") }
                }
            )
        }
    }
}
