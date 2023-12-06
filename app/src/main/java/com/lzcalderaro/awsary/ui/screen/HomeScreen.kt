package com.lzcalderaro.awsary.ui.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.lzcalderaro.awsary.ui.components.AwsIcon
import com.lzcalderaro.awsary.ui.components.SearchScreen
import com.lzcalderaro.awsary.ui.navigation.Screens
import com.lzcalderaro.awsary.viewModels.HomeScreenViewModel
import com.lzcalderaro.awsary.webservice.dto.AwsItem
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeScreen(navController: NavController) {

    ScaffoldScreen(content = { HomeContent(navController = navController) }) {}
}

@Composable
fun HomeContent(navController: NavController) {

    val viewModel = koinViewModel<HomeScreenViewModel>()
    val awsList by viewModel.filteredList.collectAsState(emptyList())

    Surface (modifier = Modifier.fillMaxSize())
    {
        Column {
            if (awsList?.isEmpty() == true) {
                Loader()
            } else {
                SearchScreen(viewModel)
                awsList.let {
                    if (it != null) {
                        itemGrid(it) {
                            val position = viewModel.completeList.value?.indexOf(it)
                            navController.navigate("${Screens.DetailScreen.name}/${position}")
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun Loader() {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        CircularProgressIndicator()
    }
}

@Composable
private fun itemGrid(awsList: List<AwsItem>, onClick: (item:AwsItem) -> Unit) {
    val listState = rememberLazyGridState()

    LazyVerticalGrid(
        columns = GridCells.Adaptive(80.dp),
        state = listState
    ) {
        items(awsList.size) { index ->
            Surface(
                onClick = { onClick(awsList[index]) }
            ) {
                AwsIcon(awsList[index]).IconList()
            }
        }
    }
}
