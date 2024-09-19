package com.lzcalderaro.awsary

import android.app.Application
import com.android.lzcalderaro.core.data.di.coreDataModule
import com.android.lzcalderaro.dictionary.data.di.dictionaryModule
import com.android.lzcalderaro.dictionary.presentation.di.dictionaryModelModule
import com.lzcalderaro.awsary.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import timber.log.Timber

class Application: Application() {

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }

        startKoin {
            androidLogger()
            androidContext(this@Application)
            modules(
                appModule,
                coreDataModule,
                dictionaryModule,
                dictionaryModelModule
            )
        }
    }
}