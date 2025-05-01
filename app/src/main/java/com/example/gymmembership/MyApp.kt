package com.example.gymmembership

import android.app.Application
import com.example.gymmembership.di.AppModule
import com.example.gymmembership.di.AppModuleImpl

class MyApp: Application() {
    companion object {
        lateinit var appModule: AppModule
    }

    override fun onCreate() {
        super.onCreate()
        appModule = AppModuleImpl(this)
    }

}