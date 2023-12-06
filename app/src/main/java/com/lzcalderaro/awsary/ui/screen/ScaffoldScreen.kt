package com.lzcalderaro.awsary.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.lzcalderaro.awsary.R


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScaffoldScreen(content: @Composable () -> Unit, navigationIcon: @Composable () -> Unit) {

    Scaffold(
        topBar = {
            TopAppBar(
                /*colors = topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),*/
                title = {
                    Text(text = stringResource(id = R.string.app_name))
                },
                navigationIcon = navigationIcon
            )
        },
    ) {
        Column(
            modifier = androidx.compose.ui.Modifier
                .padding(it),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            content()
        }
    }
}

