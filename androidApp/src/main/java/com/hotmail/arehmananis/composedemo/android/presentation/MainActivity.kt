package com.hotmail.arehmananis.composedemo.android.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import com.hotmail.arehmananis.composedemo.android.data.remote.api_service.ApiConfig
import com.hotmail.arehmananis.composedemo.android.di.injectDependencies
import org.koin.compose.KoinApplication

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                DefineNavigation()
            }
        }
    }
}

@Composable
fun ScreenPreview(
    screen: @Composable () -> Unit
) {
    KoinApplication(application = {
        modules(injectDependencies(apiConfig = ApiConfig.DEV))
    }) { screen() }
}
