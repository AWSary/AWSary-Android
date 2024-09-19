package com.android.lzcalderaro.dictionary.presentation.util

fun String.processEscapedCharacters(): String {
    return this.replace("\'", "'")
        .replace("\\n", "  \n")
}