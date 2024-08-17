package com.hotmail.arehmananis.composedemo.android.di

import com.hotmail.arehmananis.composedemo.android.data.remote.ApiServiceKtor
import com.hotmail.arehmananis.composedemo.android.data.remote.ApiServiceKtorImpl
import com.hotmail.arehmananis.composedemo.android.data.remote.api_service.ApiConfig
import com.hotmail.arehmananis.composedemo.android.data.remote.api_service.ApiManager
import com.hotmail.arehmananis.composedemo.android.data.remote.api_service.RoutesHelper
import com.hotmail.arehmananis.composedemo.android.data.repository.NewsRepositoryImpl
import com.hotmail.arehmananis.composedemo.android.domain.model.News
import com.hotmail.arehmananis.composedemo.android.domain.repository.NewsRepository
import com.hotmail.arehmananis.composedemo.android.domain.use_case.get_news.GetNewsUseCase
import com.hotmail.arehmananis.composedemo.android.presentation.news_details.DetailsViewModel
import com.hotmail.arehmananis.composedemo.android.presentation.news_list.ListViewModel
import org.koin.core.module.Module
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module


fun injectDependencies(apiConfig: ApiConfig = ApiConfig.DEV): Module {
    return module {

        single { ApiManager.getKtorHttpClient(get()) }

        single<ApiServiceKtor> { ApiServiceKtorImpl(RoutesHelper(apiConfig), get()) }

        single<NewsRepository> { NewsRepositoryImpl(get()) }

        single { GetNewsUseCase(get()) }

        viewModel { ListViewModel(get()) }

        viewModel { (news: News) -> DetailsViewModel(news) }
    }
}