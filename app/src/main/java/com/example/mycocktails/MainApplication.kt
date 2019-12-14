package com.example.mycocktails

import android.app.Application
import com.example.mycocktails.di.mainModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MainApplication)
            modules(mainModule)
        }
    }
}
