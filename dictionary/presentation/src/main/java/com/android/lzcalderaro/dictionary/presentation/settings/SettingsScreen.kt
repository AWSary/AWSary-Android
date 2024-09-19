package com.android.lzcalderaro.dictionary.presentation.settings

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MailOutline
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.android.lzcalderaro.core.presentation.designsystem.md_theme_dark_outline
import com.android.lzcalderaro.core.presentation.designsystem.md_theme_default_color
import com.android.lzcalderaro.core.presentation.ui.UiText
import com.android.lzcalderaro.dictionary.presentation.R
import com.android.lzcalderaro.dictionary.presentation.util.Email
import com.android.lzcalderaro.dictionary.presentation.util.processEscapedCharacters
import dev.jeziellago.compose.markdowntext.MarkdownText


@Composable
fun SettingsScreen(
    content: @Composable () -> Unit = {},
    onCheckChange: (Boolean) -> Unit,
    isChecked: Boolean
) {

    Column(
        modifier = Modifier.padding(10.dp)
    ) {

        ServiceLogos(onCheckChange = onCheckChange, content = content, isChecked = isChecked)
        Spacer(modifier = Modifier.height(20.dp))
        Feedback()
        Spacer(modifier = Modifier.height(20.dp))
        AboutMeScreen()
        Spacer(modifier = Modifier.height(30.dp))
    }
}

@Composable
fun AboutMeScreen() {

    Column{
        Text(text = stringResource(id = R.string.why_awsary) )

        Surface(
            color = md_theme_dark_outline.copy(alpha = 0.2f),
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(percent = 1),
        ) {

            MarkdownText(
                markdown = stringResource(id = R.string.about_me).processEscapedCharacters(),
                fontSize = 16.sp,
                color = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier.padding(10.dp)
            )
        }

    }
}

@Composable
fun ServiceLogos(
    content: @Composable () -> Unit = {},
    onCheckChange: (Boolean) -> Unit,
    isChecked: Boolean
) {
    Column {
        var checked by remember { mutableStateOf(isChecked) }
        Text(text = stringResource(id = R.string.configure_service_logo) )

        Surface(
            color = md_theme_dark_outline.copy(alpha = 0.2f),
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(percent = 5),
        ) {

            Column {
                Row(
                    modifier = Modifier.padding(5.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = stringResource(id = R.string.show_name_on_service_logo))
                    Spacer(Modifier.weight(1f))
                    Switch(
                        checked = checked,
                        onCheckedChange = {
                            onCheckChange(it)
                            checked = it
                        }
                    )

                }

                content.invoke()
            }
        }
    }
}

@Composable
fun Feedback() {

    val context = LocalContext.current

    Column{
        Text(text = stringResource(id = R.string.feedback))

        Surface(
            color = md_theme_dark_outline.copy(alpha = 0.2f),
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(percent = 5),
        ) {

            Row(
                modifier = Modifier.padding(5.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {

                IconButton(
                    onClick = { Email().send(context) }
                ) {
                    Icon(
                        Icons.Filled.MailOutline, contentDescription = stringResource(id = R.string.email) ,
                        tint = md_theme_default_color,
                    )
                }

                Column {
                    Text(
                        text = stringResource(id = R.string.send_feedback),
                        style = MaterialTheme.typography.labelLarge
                    )
                    Text(
                        text = stringResource(id = R.string.feedback_email_are_lovely_to_read),
                        style = MaterialTheme.typography.labelSmall
                    )
                }
            }
        }

    }
}