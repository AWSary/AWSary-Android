package com.lzcalderaro.awsary.ui.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.lzcalderaro.awsary.ui.navigation.SetUpNavGraph
import com.lzcalderaro.awsary.ui.theme.AppTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            AppTheme{
                SetUpNavGraph()
            }
        }
    }
}
