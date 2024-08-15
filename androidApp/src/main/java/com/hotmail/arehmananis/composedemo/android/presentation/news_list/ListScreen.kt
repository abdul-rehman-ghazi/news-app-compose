package com.hotmail.arehmananis.composedemo.android.presentation.news_list

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
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.koin.androidx.compose.koinViewModel

@Preview
@Composable
fun ListScreen(
    viewModel: ListViewModel = koinViewModel()
) {
    var keyword by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            OutlinedTextField(
                value = keyword,
                onValueChange = { value: String -> keyword = value },
                placeholder = { Text("Search here") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .padding(top = 16.dp),
                leadingIcon = {
                    Icon(
                        Icons.Sharp.Search,
                        contentDescription = "Search Icon"
                    )
                },
                shape = RoundedCornerShape(12.dp),
//                colors = OutlinedTextFieldDefaults.colors(
//                    focusedContainerColor = Color.White,
//                    unfocusedContainerColor = Color.White,
//                    disabledContainerColor = Color.White
//                )
            )
        }
    ) { innerPadding ->
        if (viewModel.newsState.value.isLoading) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator(
                    modifier = Modifier.width(64.dp),
                    color = MaterialTheme.colorScheme.secondary,
                    trackColor = MaterialTheme.colorScheme.surfaceVariant,
                    strokeWidth = 8.dp
                )
            }
        } else {
            if (viewModel.newsState.value.data != null) {
                LazyColumn(
                    Modifier
                        .fillMaxSize()
                        .padding(innerPadding),
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    contentPadding = PaddingValues(16.dp),
                ) {
                    items(viewModel.newsState.value.data!!) { news ->
                        ItemNews(news = news)
                    }
                }
            }
        }
    }
}