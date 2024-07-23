package com.android.lzcalderaro.dictionary.data

import android.content.SharedPreferences
import com.android.lzcalderaro.dictionary.domain.ServiceLogoData
import com.android.lzcalderaro.dictionary.domain.ServiceLogoStorage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class EncryptedServiceLogoStorage(
    private val sharedPreferences: SharedPreferences
): ServiceLogoStorage {


    override suspend fun get(): ServiceLogoData? {
        return withContext(Dispatchers.IO) {
            val json = sharedPreferences.getString(KEY_SERVICE_LOGO, null)
            json?.let {
                Json.decodeFromString<ServiceLogoDataSerializable>(it).toData()
            }
        }
    }

    override suspend fun set(data: ServiceLogoData?) {
        withContext(Dispatchers.IO) {
            if(data == null) {
                sharedPreferences.edit().remove(KEY_SERVICE_LOGO).apply()
                return@withContext
            }

            val json = Json.encodeToString(data.serialize())
            sharedPreferences
                .edit()
                .putString(KEY_SERVICE_LOGO, json)
                .commit()
        }
    }

    companion object {
        private const val KEY_SERVICE_LOGO = "KEY_SERVICE_LOGO"
    }
}