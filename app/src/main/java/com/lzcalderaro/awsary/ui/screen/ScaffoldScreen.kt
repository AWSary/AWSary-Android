package com.lzcalderaro.awsary.ui.screen

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.lzcalderaro.awsary.ui.components.TopBar

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ScaffoldScreen(content: @Composable () -> Unit, navigationIcon: @Composable () -> Unit) {

    var showSheet by remember { mutableStateOf(false) }

    Scaffold(
        topBar = { TopBar(navigationIcon, onClickSettings = {
            showSheet = true
        }) },
    ) {
        Column(
            modifier = Modifier.padding(PaddingValues(top = 50.dp, bottom = 10.dp, start = 10.dp, end = 10.dp))
        ) {
            content()

            if (showSheet) {
                BottomSheet() {
                    showSheet = false
                }
            }

        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomSheet(onDismiss: () -> Unit) {
    val modalBottomSheetState = rememberModalBottomSheetState()

    ModalBottomSheet(
        onDismissRequest = { onDismiss() },
        sheetState = modalBottomSheetState,
        dragHandle = { BottomSheetDefaults.DragHandle() },
    ) {
        SettingsScreen()
    }
}

