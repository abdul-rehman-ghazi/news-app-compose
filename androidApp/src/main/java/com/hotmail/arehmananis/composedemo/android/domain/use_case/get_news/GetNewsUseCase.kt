package com.hotmail.arehmananis.composedemo.android.domain.use_case.get_news

import com.hotmail.arehmananis.composedemo.android.common.Resource
import com.hotmail.arehmananis.composedemo.android.data.remote.dto.response.toNews
import com.hotmail.arehmananis.composedemo.android.domain.model.News
import com.hotmail.arehmananis.composedemo.android.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetNewsUseCase(private val newsRepository: NewsRepository) {

    operator fun invoke(page: Int): Flow<Resource<List<News>>> = flow {
        try {
            emit(Resource.Loading())
            val result = newsRepository.getTopHeadline(page).articles.map { it.toNews() }
            emit(Resource.Success(result))
        } catch (cause: Throwable) {
            emit(
                Resource.Error(
                    cause.localizedMessage
                        ?: "Couldn't reach server. Check your internet connections"
                )
            )
        }
    }
}