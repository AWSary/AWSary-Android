package com.lzcalderaro.awsary.webservice

import com.lzcalderaro.awsary.webservice.dto.AwsItem
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json

interface Service {

    suspend fun getList(): List<AwsItem>?

    companion object {
        fun create(): ServiceImp {
            return ServiceImp(
                client = HttpClient(Android) {
                    install(ContentNegotiation) {
                        json()
                    }
                }
            )
        }
    }
}