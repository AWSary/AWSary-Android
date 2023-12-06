package com.lzcalderaro.awsary.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.lzcalderaro.awsary.ui.components.AwsIcon
import com.lzcalderaro.awsary.viewModels.DetailScreenViewModel
import com.lzcalderaro.awsary.webservice.dto.AwsItem
import dev.jeziellago.compose.markdowntext.MarkdownText
import org.koin.androidx.compose.koinViewModel

@Composable
fun DetailScreen(navController: NavController, key: String) {

    ScaffoldScreen(content = { DetailContent(key = key) }) {
        IconButton(onClick = { navController.popBackStack() } ) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = "Back",
            )
        }
    }
}

@Composable
fun DetailContent(key: String) {

    val viewModel = koinViewModel<DetailScreenViewModel>()

    viewModel.loadItem(key)

    val item by viewModel.awsItem.observeAsState()

    Surface(
        modifier = Modifier.padding(15.dp)
    ) {
        Column {
            item.let {
                if (it != null) {
                    AwsIcon(it).IconHeader()
                    Content(it)
                }
            }
        }
    }
}

@Composable
fun Content(item: AwsItem) {
    Surface {
        MarkdownText(
            markdown = item.shortDescription,
            fontSize = 16.sp,
            color = MaterialTheme.colorScheme.onBackground
        )
    }
}

