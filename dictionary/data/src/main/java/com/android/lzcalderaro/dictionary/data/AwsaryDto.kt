package com.android.lzcalderaro.dictionary.data

import kotlinx.serialization.Serializable

@Serializable
data class AwsaryDto(
    val shortDescription: String,
    val imageURL: String,
    val longName: String,
    val youtubeId: String,
    val id: Int,
    val name: String
)
