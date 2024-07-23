package com.android.lzcalderaro.dictionary.presentation.list

import com.android.lzcalderaro.dictionary.domain.AwsaryIconData

data class ListState(
    val isFetching: Boolean = false,
    val dictionaryData: List<AwsaryIconData> = emptyList(),
    val isAlternative: Boolean = false
)
