package com.hotmail.arehmananis.composedemo.android.presentation.news_list

import android.content.Context
import android.net.Uri
import androidx.browser.customtabs.CustomTabsIntent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hotmail.arehmananis.composedemo.android.data.remote.ApiServiceKtorImpl
import com.hotmail.arehmananis.composedemo.android.data.remote.api_service.ApiConfig
import com.hotmail.arehmananis.composedemo.android.data.remote.api_service.RoutesHelper
import com.hotmail.arehmananis.composedemo.android.data.repository.NewsRepositoryImpl
import com.hotmail.arehmananis.composedemo.android.domain.model.News
import com.hotmail.arehmananis.composedemo.android.domain.use_case.get_news.GetNewsUseCase
import com.hotmail.arehmananis.composedemo.android.presentation.launchChromeTab
import io.ktor.client.HttpClient
import org.koin.androidx.compose.koinViewModel

@Composable
fun ListScreen(
    navigateToDetailsScreen: (News) -> Unit = { },
    viewModel: ListViewModel = koinViewModel()
) {
    val context = LocalContext.current

    val keyboardController = LocalSoftwareKeyboardController.current
    val focusRequester = remember { FocusRequester() }
    val focusManager = LocalFocusManager.current

    var keyword by rememberSaveable { mutableStateOf("") }

    Scaffold(
        topBar = {
            OutlinedTextField(
                value = keyword,
                onValueChange = { value: String -> keyword = value },
                placeholder = { Text("Search here") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .padding(top = 16.dp)
                    .focusRequester(focusRequester),
                leadingIcon = {
                    Icon(
                        Icons.Sharp.Search,
                        contentDescription = "Search Icon"
                    )
                },
                shape = RoundedCornerShape(12.dp),
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Search
                ),
                keyboardActions = KeyboardActions(
                    onSearch = {
                        viewModel.getNews(1, keyword.ifBlank { null })
                        keyboardController?.hide()
                        focusManager.clearFocus()
                    }
                ),
                singleLine = true,
            )
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            contentAlignment = Alignment.Center,
        ) {
            if (viewModel.newsState.value.isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier.width(64.dp),
                    color = MaterialTheme.colorScheme.secondary,
                    trackColor = MaterialTheme.colorScheme.surfaceVariant,
                    strokeWidth = 8.dp
                )
            } else {
                if (viewModel.newsState.value.data != null) {
                    LazyColumn(
                        Modifier
                            .fillMaxSize(),
                        verticalArrangement = Arrangement.spacedBy(8.dp),
                        contentPadding = PaddingValues(16.dp),
                    ) {
                        items(viewModel.newsState.value.data ?: emptyList()) { news ->
                            ItemNews(news = news, onItemClick = {
                                if (news.content.isNullOrBlank()) {
                                    launchChromeTab(context, news.url)
                                } else {
                                    navigateToDetailsScreen(news)
                                }
                            })
                        }
                    }
                } else if (viewModel.newsState.value.error != null) {
                    Text(
                        text = viewModel.newsState.value.error ?: "",
                        color = MaterialTheme.colorScheme.error,
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }


}

@Preview
@Composable
fun MyScreenPreview() {
    ListScreen(
        viewModel = ListViewModel(
            GetNewsUseCase(
                NewsRepositoryImpl(ApiServiceKtorImpl(RoutesHelper(ApiConfig.DEV), HttpClient()))
            )
        )
    )
}