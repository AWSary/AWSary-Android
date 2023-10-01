package com.lzcalderaro.awsary.webservice

import android.R
import android.util.Log
import com.lzcalderaro.awsary.webservice.Routes.BASE_URL
import com.lzcalderaro.awsary.webservice.dto.AwsItem
import io.ktor.client.HttpClient
import io.ktor.client.call.NoTransformationFoundException
import io.ktor.client.call.body
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.RedirectResponseException
import io.ktor.client.plugins.ServerResponseException
import io.ktor.client.request.get

class ServiceImp(private val client:HttpClient): Service {

    override suspend fun getList(): List<AwsItem>? {
        return try {
            client.get(BASE_URL).body<List<AwsItem>>()
        } catch(e: RedirectResponseException) {
            // 3xx - responses
            Log.d("AWSARYDEBUG","Error: ${e.response.status.description}")
            null
        } catch(e: ClientRequestException) {
            // 4xx - responses
            Log.d("AWSARYDEBUG","Error: ${e.response.status.description}")
            null
        } catch(e: ServerResponseException) {
            // 5xx - responses
            Log.d("AWSARYDEBUG","Error: ${e.response.status.description}")
            null
        } catch (e: NoTransformationFoundException) {

            val request: String = client.get(BASE_URL).body()
            val json = kotlinx.serialization.json.Json {
                ignoreUnknownKeys = true
            }

            json.decodeFromString(request)

        } catch(e: Exception) {
            Log.d("AWSARYDEBUG","Error: ${e.message}")
            null
        }
    }
}