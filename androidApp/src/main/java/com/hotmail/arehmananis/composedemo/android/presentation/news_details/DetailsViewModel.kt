package com.hotmail.arehmananis.composedemo.android.presentation.news_details

import android.util.Log
import androidx.lifecycle.ViewModel
import com.hotmail.arehmananis.composedemo.android.domain.model.News

class DetailsViewModel(news: News) : ViewModel() {
    companion object {
        private val TAG = DetailsViewModel::class.java.simpleName
    }

    init {
        Log.i(TAG, "init: $news")
    }
}