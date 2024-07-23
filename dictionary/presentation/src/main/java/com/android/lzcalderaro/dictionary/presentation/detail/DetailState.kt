package com.android.lzcalderaro.dictionary.presentation.detail

import com.android.lzcalderaro.dictionary.domain.AwsaryData

data class DetailState(
    val isFetching: Boolean = false,
    val data: AwsaryData? = null,
    val isAlternative: Boolean = false
)