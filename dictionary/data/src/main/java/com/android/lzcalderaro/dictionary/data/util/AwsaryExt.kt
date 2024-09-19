package com.android.lzcalderaro.dictionary.data.util

import com.android.lzcalderaro.dictionary.data.AwsaryDto
import com.android.lzcalderaro.dictionary.domain.AwsaryData
import com.android.lzcalderaro.dictionary.domain.AwsaryIconData

fun AwsaryDto.toData() : AwsaryData {
    return AwsaryData(
        id = this.id,
        imageURL = this.imageURL,
        shortDescription = this.shortDescription,
        longName = this.longName,
        youtubeId = this.youtubeId,
        name = this.name
    )
}

fun AwsaryDto.toIconData() : AwsaryIconData {
    return AwsaryIconData(
        id = this.id,
        image = this.imageURL,
        name = this.name
    )
}

