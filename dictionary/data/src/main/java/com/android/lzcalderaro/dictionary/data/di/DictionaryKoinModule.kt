package com.android.lzcalderaro.dictionary.data.di

import com.android.lzcalderaro.dictionary.data.DictionaryRepositoryImp
import com.android.lzcalderaro.dictionary.data.EncryptedServiceLogoStorage
import com.android.lzcalderaro.dictionary.domain.DictionaryRepository
import com.android.lzcalderaro.dictionary.domain.ServiceLogoStorage
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val dictionaryModule = module {
    singleOf(::DictionaryRepositoryImp).bind<DictionaryRepository>()
    singleOf(::EncryptedServiceLogoStorage).bind<ServiceLogoStorage>()
}