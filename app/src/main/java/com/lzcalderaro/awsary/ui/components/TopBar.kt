package com.lzcalderaro.awsary.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.twotone.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.lzcalderaro.awsary.R
import com.lzcalderaro.awsary.ui.theme.md_theme_default_color

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(navigationIcon: @Composable () -> Unit, onClickSettings: () -> Unit) {
    TopAppBar(
        title = {
            Surface {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Text(
                        text = stringResource(id = R.string.app_name),
                        fontWeight = FontWeight.Bold,
                        fontSize = 25.sp
                    )

                    Row(
                        horizontalArrangement = Arrangement.End,
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        IconButton(
                            onClick = { onClickSettings() },
                            modifier = Modifier.align(Alignment.CenterVertically)
                        ) {
                            Icon(
                                Icons.TwoTone.Settings,
                                //Icons.Filled.Settings,
                                contentDescription = "search icon",
                                tint = md_theme_default_color
                            )
                        }
                    }
                }
            }
        },
        navigationIcon = navigationIcon
    )
}