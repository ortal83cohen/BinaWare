package com.cohen.binaware

import android.app.Application
import com.cohen.binaware.helpers.di.applicationModule
import com.cohen.binaware.helpers.di.viewModelModule

import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class BaseApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            // Android context
            androidContext(this@BaseApplication)
            // modules
            modules(applicationModule)
            modules(viewModelModule)


        }
    }
}