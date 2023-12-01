package com.lzcalderaro.awsary.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lzcalderaro.awsary.ui.components.IconHeader
import com.lzcalderaro.awsary.ui.screen.ScaffoldScreen
import com.lzcalderaro.awsary.viewModels.AwsServicesViewModel
import dev.jeziellago.compose.markdowntext.MarkdownText
import org.koin.androidx.viewmodel.ext.android.activityViewModel

class Single : Fragment() {

    private val awsViewModel by activityViewModel<AwsServicesViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return ComposeView(requireActivity()).apply {
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {
                ScaffoldScreen {
                    SingleScreen()
                }
            }
        }

    }

    @Composable
    fun SingleScreen() {

        if (awsViewModel.selectedItem == null) {
            return
        }

        Surface(
            modifier = Modifier.padding(15.dp)
        ) {
            Column {
                IconHeader(awsViewModel.selectedItem!!)
                Content()
            }
        }
    }

    @Composable
    fun Content() {
        awsViewModel.selectedItem?.let {
            Surface {
                MarkdownText(
                    markdown = it.shortDescription,
                    fontSize = 16.sp,
                    color = MaterialTheme.colorScheme.onBackground
                )
            }
        }
    }
}
