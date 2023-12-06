package com.lzcalderaro.awsary.ui.activities

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import com.lzcalderaro.awsary.ui.navigation.SetUpNavGraph
import com.lzcalderaro.awsary.ui.theme.AppTheme

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            AppTheme{
                SetUpNavGraph()
            }
        }
    }
}
