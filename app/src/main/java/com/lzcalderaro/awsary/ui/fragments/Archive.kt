package com.lzcalderaro.awsary.ui.fragments

import android.content.ClipData
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.lzcalderaro.awsary.R
import com.lzcalderaro.awsary.ui.components.AwsIcon
import com.lzcalderaro.awsary.ui.components.SearchBar
import com.lzcalderaro.awsary.ui.screen.ScaffoldScreen
import com.lzcalderaro.awsary.viewModels.AwsServicesViewModel
import com.lzcalderaro.awsary.webservice.dto.AwsItem
import org.koin.androidx.viewmodel.ext.android.activityViewModel
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment

class Archive : Fragment() {

    private val awsViewModel by activityViewModel<AwsServicesViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
       return ComposeView(requireActivity()).apply {
           setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
           setContent {
               ScaffoldScreen {
                   ArchiveScreen()
               }
           }
       }
    }

    @Composable
    fun ArchiveScreen() {

        val awsList by awsViewModel.filteredList.observeAsState(emptyList())

        LaunchedEffect(Unit) {
            awsViewModel.getAwsServices()
        }

        Surface (modifier = Modifier.fillMaxSize())
        {
            Column {
                if (awsList?.isEmpty() == true) {
                    Loader()
                } else {
                    SearchBar(searchDisplay = "", onSearchDisplayChanged = awsViewModel::onSearch)
                    awsList?.let { itemGrid(it) }
                }
            }
        }
    }

    @Composable
    fun Loader() {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            CircularProgressIndicator()
        }
    }

    @Composable
    private fun itemGrid(awsList: List<AwsItem>) {

        val listState = rememberLazyGridState()

        LazyVerticalGrid(
            columns = GridCells.Adaptive(80.dp),
            state = listState
        ) {
            items(awsList.size) { index ->
                AwsIcon(awsList[index], ::goToSingle)
            }
        }
    }

    private fun goToSingle(item: AwsItem) {
        awsViewModel.selectedItem = item
        findNavController().navigate(R.id.action_ArchiveFragment_to_SingleFragment)
    }
}