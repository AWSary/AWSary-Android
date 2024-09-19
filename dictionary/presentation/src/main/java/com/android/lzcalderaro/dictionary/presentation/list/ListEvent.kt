package com.android.lzcalderaro.dictionary.presentation.list

import com.android.lzcalderaro.core.presentation.ui.UiText

sealed interface ListEvent {
    data class Error(val error: UiText): ListEvent
}