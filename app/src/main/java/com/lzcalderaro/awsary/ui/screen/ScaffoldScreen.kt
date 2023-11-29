package com.lzcalderaro.awsary.ui.screen

import androidx.compose.runtime.Composable
import com.lzcalderaro.awsary.ui.theme.AppTheme


@Composable
fun ScaffoldScreen(content: @Composable () -> Unit) {
    AppTheme {
        content()
    }
}
