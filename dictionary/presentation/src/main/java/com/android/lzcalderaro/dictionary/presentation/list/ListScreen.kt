package com.android.lzcalderaro.dictionary.presentation.list

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.twotone.Settings
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.android.lzcalderaro.core.presentation.designsystem.AppTheme
import com.android.lzcalderaro.core.presentation.designsystem.components.IndeterminateCircularIndicator
import com.android.lzcalderaro.core.presentation.designsystem.components.ListIcon
import com.android.lzcalderaro.core.presentation.designsystem.components.ListIconAlternative
import com.android.lzcalderaro.core.presentation.designsystem.components.ScaffoldScreen
import com.android.lzcalderaro.core.presentation.designsystem.components.SearchContent
import com.android.lzcalderaro.core.presentation.designsystem.components.TopBar
import com.android.lzcalderaro.core.presentation.designsystem.md_theme_default_color
import com.android.lzcalderaro.core.presentation.ui.ObserveAsEvents
import com.android.lzcalderaro.dictionary.presentation.settings.SettingsScreen
import org.koin.androidx.compose.koinViewModel

@Composable
fun ListScreenRoot(
    viewModel: ListViewModel = koinViewModel(),
    onClickItem: (Int) -> Unit
) {

    val context = LocalContext.current

    ObserveAsEvents(flow = viewModel.events) { event ->
        when(event) {
            is ListEvent.Error -> {
                Toast.makeText(
                    context,
                    event.error.asString(context),
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }

    ListScreen(
        state = viewModel.state,
        onAction = {
            when(it) {
                is ListAction.OnClickItem -> {
                    onClickItem(it.id)
                }
                is ListAction.Searching -> {
                    viewModel.onAction(ListAction.Searching(it.text))
                }
                is ListAction.OnChangeServiceLogo -> {
                    viewModel.onAction(ListAction.OnChangeServiceLogo(it.isAlternative))
                }
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListScreen(
    state: ListState,
    onAction: (ListAction) -> Unit
) {

    var showSheet by remember { mutableStateOf(false) }

    ScaffoldScreen(
        topAppBar = {
            TopBar(showBackButton = false, endContent = { SettingsIcon {
                showSheet = true
            } })
        }
    ) { padding ->

        when(state.isFetching) {
            true -> IndeterminateCircularIndicator()
            false -> {
                val listState = rememberLazyGridState()
                var fieldValue: String by rememberSaveable { mutableStateOf("") }

                Column(
                    modifier = Modifier.padding(padding)
                ) {

                    SearchContent(
                        searchDisplay = fieldValue,
                        onSearchDisplayChanged = {
                            fieldValue = it
                            onAction(ListAction.Searching(it))
                        }
                    )

                    LazyVerticalGrid(
                        columns = GridCells.Adaptive(100.dp),
                        state = listState,
                    ) {
                        items(state.dictionaryData.size) { index ->
                            when(state.isAlternative) {
                                true -> {
                                    ListIconAlternative(
                                        imageUrl = state.dictionaryData[index].image,
                                        title = state.dictionaryData[index].name,
                                        modifier = Modifier
                                    ) {
                                        onAction(ListAction.OnClickItem(state.dictionaryData[index].id))
                                    }
                                }
                                false -> {
                                    ListIcon(
                                        imageUrl = state.dictionaryData[index].image,
                                        title = state.dictionaryData[index].name,
                                        modifier = Modifier
                                    ) {
                                        onAction(ListAction.OnClickItem(state.dictionaryData[index].id))
                                    }
                                }
                            }

                        }
                    }
                }
            }
        }

        if (showSheet) {
            BottomSheet(
                state = state,
                onAction = onAction,
                onDismiss = {showSheet = false}
            )
        }

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomSheet(
    state: ListState,
    onAction: (ListAction) -> Unit,
    onDismiss: () -> Unit
) {
    val modalBottomSheetState = rememberModalBottomSheetState()

    ModalBottomSheet(
        onDismissRequest = { onDismiss() },
        sheetState = modalBottomSheetState,
        dragHandle = { BottomSheetDefaults.DragHandle() },
    ) {
        SettingsScreen(
            content = {
                when(state.isFetching) {
                    true -> Unit
                    false -> {
                        val item = state.dictionaryData.random()

                        Row {
                            ListIcon(
                                imageUrl = item.image,
                                title = item.name,
                                modifier = Modifier
                                    .width(150.dp) // Set a specific width
                                    .aspectRatio(1f), // Maintain aspect ratio
                                onClick = {}
                            )

                            Spacer(modifier = Modifier.width(10.dp))

                            ListIconAlternative(
                                imageUrl = item.image,
                                title = item.name,
                                modifier = Modifier
                                    .width(150.dp),
                                onClick = {}
                            )

                        }
                    }
                }

            },
            onCheckChange = {
                onAction(ListAction.OnChangeServiceLogo(it))
            },
            isChecked = state.isAlternative
        )
    }
}

@Composable
fun SettingsIcon(
    onClickItem: () -> Unit
) {

    Row(
        horizontalArrangement = Arrangement.End,
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        IconButton(
            onClick = { onClickItem() },
            modifier = Modifier.align(Alignment.CenterVertically)
        ) {
            Icon(
                Icons.TwoTone.Settings,
                contentDescription = "search icon",
                tint = md_theme_default_color
            )
        }
    }
}

@Preview
@Composable
fun ListScreenPreview() {
    AppTheme {
        ListScreen(
            state = ListState(),
            onAction = {}
        )
    }
}
