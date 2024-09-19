package com.android.lzcalderaro.dictionary.presentation.detail

sealed interface DetailAction {
    data class LoadItem(val id: Int): DetailAction
    data object OnBack: DetailAction
}