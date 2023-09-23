package com.lzcalderaro.awsary.webservice.dto

import kotlinx.serialization.Serializable

@Serializable
data class AwsList(
    val list: List<AwsItem>
)