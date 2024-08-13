package com.hotmail.arehmananis.composedemo.android.presentation

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.hotmail.arehmananis.composedemo.android.presentation.news_list.ListScreen
import org.koin.compose.KoinApplication

@Composable
@Preview
fun App() {
    KoinApplication(application = {}) {
        MyApplicationTheme {
            ListScreen()
        }
    }
}