package com.android.lzcalderaro.dictionary.presentation.di

import com.android.lzcalderaro.dictionary.presentation.detail.DetailViewModel
import com.android.lzcalderaro.dictionary.presentation.list.ListViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val dictionaryModelModule = module {
    viewModelOf(::ListViewModel)
    viewModelOf(::DetailViewModel)
}