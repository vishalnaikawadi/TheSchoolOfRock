package com.vmn.theschoolofrock

import android.app.Application
import com.vmn.data.di.dataModule
import com.vmn.domain.di.domainModule
import com.vmn.theschoolofrock.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class GenericApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        initKoin()
    }

    private fun initKoin() {
        startKoin {
            modules(dataModule, domainModule, appModule)
            androidContext(this@GenericApplication)
        }
    }
}