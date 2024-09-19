package com.android.lzcalderaro.dictionary.domain

import com.android.lzcalderaro.core.domain.util.DataError
import com.android.lzcalderaro.core.domain.util.Result

interface DictionaryRepository {
    suspend fun fetchList(): Result<List<AwsaryIconData>, DataError.Network>
    suspend fun fetchItem(id: Int): Result<List<AwsaryData>, DataError.Network>
}