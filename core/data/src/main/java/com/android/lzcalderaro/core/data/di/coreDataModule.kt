package com.android.lzcalderaro.core.data.di

import com.android.lzcalderaro.core.data.networking.HttpClientFactory
import org.koin.dsl.module

val coreDataModule = module {
    single {
        HttpClientFactory().build()
    }
}