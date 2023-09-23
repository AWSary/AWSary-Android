package com.lzcalderaro.awsary.utils

import android.content.Context
import android.util.Log
import com.lzcalderaro.awsary.R
import com.lzcalderaro.awsary.webservice.dto.AwsItem
import kotlinx.serialization.json.Json
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.io.Reader
import java.io.StringWriter
import java.io.Writer

class LocalResources(private val context:Context) {

    fun load(): List<AwsItem> {
        val inputStream: InputStream = context.resources.openRawResource(R.raw.services)
        val writer: Writer = StringWriter()
        val buffer = CharArray(1024)
        return inputStream.use { inputStream ->
            val reader: Reader = BufferedReader(InputStreamReader(inputStream, "UTF-8"))
            var n: Int
            while (reader.read(buffer).also { n = it } != -1) {
                writer.write(buffer, 0, n)
            }

            val jsonString: String = writer.toString()
            Json.decodeFromString(jsonString)
        }
    }
}