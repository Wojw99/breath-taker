package com.example.breathtaker

import android.app.Application
import com.example.breathtaker.di.AppModule
import com.example.breathtaker.di.AppModuleImpl

class BreathTakerApplication: Application() {
    companion object {
        lateinit var appModule: AppModule
    }

    override fun onCreate() {
        super.onCreate()
        appModule = AppModuleImpl(this)
    }
}