package com.android.lzcalderaro.core.presentation.designsystem.components

import androidx.compose.foundation.layout.Row
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.android.lzcalderaro.core.presentation.designsystem.AppTheme
import com.android.lzcalderaro.core.presentation.designsystem.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    showBackButton: Boolean,
    modifier: Modifier = Modifier,
    onMenuItemClick: (Int) -> Unit = {},
    onBackClick: () -> Unit = {},
    scrollBehavior: TopAppBarScrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(),
    startContent: (@Composable () -> Unit)? = null,
    endContent: (@Composable () -> Unit)? = null

) {
    TopAppBar(
        title = {
            Surface {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    startContent?.invoke()
                    Text(
                        text = stringResource(id = R.string.app_name),
                        fontWeight = FontWeight.Bold,
                        fontSize = 25.sp
                    )
                    endContent?.invoke()
                }
            }
        },
        scrollBehavior = scrollBehavior,
        navigationIcon = {
            if(showBackButton) {
                IconButton(onClick = onBackClick) {
                    Icon(
                        imageVector = ArrowLeftIcon,
                        contentDescription = stringResource(id = R.string.go_back),
                        tint = MaterialTheme.colorScheme.onBackground
                    )
                }
            }
        }
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun TopBarPreview() {
    AppTheme {
        TopBar(
            showBackButton = true
        )
    }
}