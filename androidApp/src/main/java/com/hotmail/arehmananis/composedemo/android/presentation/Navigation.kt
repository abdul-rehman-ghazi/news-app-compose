package com.hotmail.arehmananis.composedemo.android.presentation

import android.content.Context
import android.net.Uri
import androidx.browser.customtabs.CustomTabsIntent
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.hotmail.arehmananis.composedemo.android.domain.model.News
import com.hotmail.arehmananis.composedemo.android.presentation.news_details.DetailsScreen
import com.hotmail.arehmananis.composedemo.android.presentation.news_list.ListScreen
import kotlinx.serialization.Serializable
import kotlin.reflect.typeOf

@Serializable
object NewsList

@Serializable
data class NewsDetail(val news: News)


@Composable
fun DefineNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = NewsList) {
        composable<NewsList> {
            ListScreen(navigateToDetailsScreen = { news ->
                navController.navigate(NewsDetail(news))
            })
        }
        composable<NewsDetail>(
            typeMap = mapOf(typeOf<News>() to CustomNavType.NewsType)
        ) {
            val args = it.toRoute<NewsDetail>()
            DetailsScreen(
                news = args.news,
                navigateBack = { navController.popBackStack() }
            )
        }
    }
}

fun launchChromeTab(context: Context, url: String) {
    val builder = CustomTabsIntent.Builder()
    val customTabsIntent = builder.build()
    customTabsIntent.launchUrl(context, Uri.parse(url))
}