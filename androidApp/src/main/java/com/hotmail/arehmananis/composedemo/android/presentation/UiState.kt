package com.hotmail.arehmananis.composedemo.android.presentation

data class UiState<T>(
    val isLoading: Boolean = false,
    val data: T? = null,
    val error: String? = null
)