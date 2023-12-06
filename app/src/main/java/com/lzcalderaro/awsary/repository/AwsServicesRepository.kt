package com.lzcalderaro.awsary.repository

import com.lzcalderaro.awsary.webservice.dto.AwsItem
import kotlinx.coroutines.flow.Flow

interface AwsServicesRepository {
    fun fetchList(): Flow<List<AwsItem>>
    fun fetchItem(position: String): Flow<AwsItem>
}