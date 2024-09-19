package com.android.lzcalderaro.dictionary.presentation.list

sealed interface ListAction {
    data class OnClickItem(val id: Int): ListAction
    data class Searching(val text: String): ListAction
    data class OnChangeServiceLogo(val isAlternative: Boolean): ListAction
}