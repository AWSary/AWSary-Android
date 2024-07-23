package com.android.lzcalderaro.dictionary.domain

interface ServiceLogoStorage {
    suspend fun get(): ServiceLogoData?
    suspend fun set(data: ServiceLogoData?)
}