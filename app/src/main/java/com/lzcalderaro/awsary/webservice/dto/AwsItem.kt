package com.lzcalderaro.awsary.webservice.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AwsItem(
    val shortDescription: String,
    val imageURL: String,
    val longName: String,
    @SerialName("youtube_id") val youtubeId: String,
    val id: Int,
    val name: String
)