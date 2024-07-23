package com.android.lzcalderaro.dictionary.presentation.detail

import com.android.lzcalderaro.core.presentation.ui.UiText

sealed interface DetailEvent {
    data class Error(val error: UiText): DetailEvent
}