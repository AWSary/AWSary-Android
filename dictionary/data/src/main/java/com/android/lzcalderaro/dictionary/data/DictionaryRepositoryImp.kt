package com.android.lzcalderaro.dictionary.data

import com.android.lzcalderaro.core.data.networking.get
import com.android.lzcalderaro.core.domain.util.DataError
import com.android.lzcalderaro.core.domain.util.Result
import com.android.lzcalderaro.core.domain.util.map
import com.android.lzcalderaro.dictionary.data.util.toData
import com.android.lzcalderaro.dictionary.data.util.toIconData
import com.android.lzcalderaro.dictionary.domain.AwsaryData
import com.android.lzcalderaro.dictionary.domain.AwsaryIconData
import com.android.lzcalderaro.dictionary.domain.DictionaryRepository
import io.ktor.client.HttpClient

class DictionaryRepositoryImp(
    private val httpClient: HttpClient
): DictionaryRepository {

    private suspend fun getList() = httpClient.get<List<AwsaryDto>>(
        route = "/aws_services.json"
    )

    override suspend fun fetchList(): Result<List<AwsaryIconData>, DataError.Network> {
        return getList().map { item ->
            item.map {
                it.toIconData()
            }
        }
    }

    override suspend fun fetchItem(id: Int): Result<List<AwsaryData>, DataError.Network> {

        return getList().map { item ->
            item.map {
                it.toData()
            }.filter {
                id == it.id
            }
        }
    }
}