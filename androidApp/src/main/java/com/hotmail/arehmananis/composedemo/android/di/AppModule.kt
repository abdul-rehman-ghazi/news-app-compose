package com.hotmail.arehmananis.composedemo.android.di

import com.hotmail.arehmananis.composedemo.android.data.remote.api_service.ApiManager
import com.hotmail.arehmananis.composedemo.android.data.repository.NewsRepositoryImpl
import com.hotmail.arehmananis.composedemo.android.domain.repository.NewsRepository
import com.hotmail.arehmananis.composedemo.android.domain.use_case.get_news.GetNewsUseCase
import com.hotmail.arehmananis.composedemo.android.presentation.news_list.ListViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    single { ApiManager.getKtorHttpClient(get()) }

    single<NewsRepository> { NewsRepositoryImpl(get()) }

    single { GetNewsUseCase(get()) }

    viewModel { ListViewModel(get()) }
}