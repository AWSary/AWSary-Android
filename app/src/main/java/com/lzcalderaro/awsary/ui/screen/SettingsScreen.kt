package com.lzcalderaro.awsary.ui.screen

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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lzcalderaro.awsary.src.Email
import com.lzcalderaro.awsary.src.Texts.ABOUT_ME_TEXT
import com.lzcalderaro.awsary.ui.theme.md_theme_dark_outline
import com.lzcalderaro.awsary.ui.theme.md_theme_default_color
import dev.jeziellago.compose.markdowntext.MarkdownText


@Composable
fun SettingsScreen() {

    Column(
        modifier = Modifier.padding(10.dp)
    ) {
        Feedback()
        Spacer(modifier = Modifier.height(20.dp))
        AboutMeScreen()
        Spacer(modifier = Modifier.height(30.dp))
    }
}

@Composable
fun AboutMeScreen() {

    Column{
        Text(text = "Why AWSary")

        Surface(
            color = md_theme_dark_outline.copy(alpha = 0.2f),
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(percent = 1),
        ) {

            MarkdownText(
                markdown = ABOUT_ME_TEXT,
                fontSize = 16.sp,
                color = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier.padding(10.dp)
            )
        }

    }
    
}

@Composable
fun Feedback() {

    val context = LocalContext.current

    Column{
        Text(text = "Feedback")

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
                        Icons.Filled.MailOutline, contentDescription = "Email",
                        tint = md_theme_default_color,
                    )
                }

                Column {
                    Text(text = "Send Feedback", style = MaterialTheme.typography.labelLarge)
                    Text(text = "Feedback emails are lovely to read!", style = MaterialTheme.typography.labelSmall)
                }
            }
        }

    }
}
