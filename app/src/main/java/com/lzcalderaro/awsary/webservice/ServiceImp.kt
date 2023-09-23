package com.lzcalderaro.awsary.webservice

import android.util.Log
import com.lzcalderaro.awsary.webservice.Routes.BASE_URL
import com.lzcalderaro.awsary.webservice.dto.AwsList
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.RedirectResponseException
import io.ktor.client.plugins.ServerResponseException
import io.ktor.client.request.get

class ServiceImp(private val client:HttpClient): Service {

    override suspend fun getList(): AwsList? {
        return try {
            client.get(BASE_URL).body<AwsList>()
        } catch(e: RedirectResponseException) {
            // 3xx - responses
            Log.d("AWSary","Error: ${e.response.status.description}")
            null
        } catch(e: ClientRequestException) {
            // 4xx - responses
            Log.d("AWSary","Error: ${e.response.status.description}")
            null
        } catch(e: ServerResponseException) {
            // 5xx - responses
            Log.d("AWSary","Error: ${e.response.status.description}")
            null
        } catch(e: Exception) {
            Log.d("AWSary","Error: ${e.message}")
            null
        }
    }
}