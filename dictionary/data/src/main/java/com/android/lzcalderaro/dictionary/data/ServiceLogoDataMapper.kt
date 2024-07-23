package com.android.lzcalderaro.dictionary.data

import com.android.lzcalderaro.dictionary.domain.ServiceLogoData

fun ServiceLogoData.serialize(): ServiceLogoDataSerializable {
    return ServiceLogoDataSerializable(
        alternative = this.alternative
    )
}

fun ServiceLogoDataSerializable.toData(): ServiceLogoData {
    return ServiceLogoData(
        alternative = this.alternative
    )
}