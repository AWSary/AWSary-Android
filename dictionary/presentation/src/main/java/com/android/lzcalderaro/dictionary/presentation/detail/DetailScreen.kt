package com.android.lzcalderaro.dictionary.presentation.detail

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.android.lzcalderaro.core.presentation.designsystem.components.IndeterminateCircularIndicator
import com.android.lzcalderaro.core.presentation.designsystem.components.ListIcon
import com.android.lzcalderaro.core.presentation.designsystem.components.ListIconAlternative
import com.android.lzcalderaro.core.presentation.designsystem.components.ScaffoldScreen
import com.android.lzcalderaro.core.presentation.designsystem.components.TopBar
import com.android.lzcalderaro.core.presentation.ui.ObserveAsEvents
import dev.jeziellago.compose.markdowntext.MarkdownText
import org.koin.androidx.compose.koinViewModel

@Composable
fun DetailScreenRoot(
    viewModel: DetailViewModel = koinViewModel(),
    onBack: () -> Unit,
    id: Int
) {

    val context = LocalContext.current

    ObserveAsEvents(flow = viewModel.events) { event ->
        when(event) {
            is DetailEvent.Error -> {
                Toast.makeText(
                    context,
                    event.error.asString(context),
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }
    
    LaunchedEffect(Unit) {
        viewModel.onAction(DetailAction.LoadItem(id))
    }

    DetailScreen(
        state = viewModel.state,
        onBackClick = {
            onBack()
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(
    state: DetailState,
    onBackClick: (DetailAction) -> Unit
) {
    ScaffoldScreen(
        topAppBar = {
            TopBar(
                showBackButton = true,
                onBackClick = {
                    onBackClick(DetailAction.OnBack)
                }
            )
        }
    ) { padding ->

        when(state.isFetching) {
            true -> IndeterminateCircularIndicator()
            false -> {
                state.data?.let {
                    Column(
                        modifier = Modifier.padding(padding).fillMaxSize()
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Absolute.Center,
                            modifier = Modifier.fillMaxWidth()
                        ) {

                            when(state.isAlternative) {
                                true -> {
                                    ListIconAlternative(
                                        imageUrl = state.data.imageURL,
                                        title = state.data.name,
                                        modifier = Modifier.widthIn(max = 120.dp),
                                        onClick = {}
                                    )
                                }
                                false -> {
                                    ListIcon(
                                        imageUrl = state.data.imageURL,
                                        title = "",
                                        modifier = Modifier.size(120.dp).aspectRatio(1f),
                                        onClick = {}
                                    )
                                }
                            }

                            Spacer(modifier = Modifier.width(3.dp))

                            Text(
                                text = state.data.longName,
                                fontSize = 18.sp,
                                textAlign = TextAlign.Start,
                                fontWeight = FontWeight.Bold,
                            )
                        }

                        MarkdownText(
                            modifier = Modifier.padding(15.dp),
                            markdown = state.data.shortDescription,
                            fontSize = 16.sp,
                            color = MaterialTheme.colorScheme.onBackground
                        )
                    }
                }
            }
        }
    }
}