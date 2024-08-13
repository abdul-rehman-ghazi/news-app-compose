package com.hotmail.arehmananis.composedemo.android.presentation

import android.app.Application
import com.hotmail.arehmananis.composedemo.android.data.remote.api_service.ApiConfig
import com.hotmail.arehmananis.composedemo.android.di.injectDependencies
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@MyApplication)
            modules(injectDependencies(ApiConfig.DEV))
        }
    }
}