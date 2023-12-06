package com.lzcalderaro.awsary.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.lzcalderaro.awsary.viewModels.HomeScreenViewModel

@Composable
fun SearchScreen(awsViewModel: HomeScreenViewModel = viewModel()) {
    var fieldValue: String by rememberSaveable { mutableStateOf("") }

    awsViewModel.onValueChange(fieldValue)
    SearchContent(searchDisplay = fieldValue, onSearchDisplayChanged = {
        fieldValue = it
        awsViewModel.onValueChange(it)
    } )
}

@Composable
fun SearchContent(
    searchDisplay: String,
    onSearchDisplayChanged: (String) -> Unit,
    modifier: Modifier = Modifier,
) {

    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {

        val keyboardController = LocalSoftwareKeyboardController.current
        val focusManager = LocalFocusManager.current

        OutlinedTextField(
            value = searchDisplay,
            onValueChange = onSearchDisplayChanged,
            label = { Text(text = "Search") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            shape = RoundedCornerShape(percent = 20),
            leadingIcon = { SearchIcon() },
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
            keyboardActions = KeyboardActions(onSearch = {
                onSearchDisplayChanged(searchDisplay)
                // Hide the keyboard after submitting the search
                keyboardController?.hide()
                //or hide keyboard
                focusManager.clearFocus()
            })
        )
    }
}

@Composable
private fun SearchIcon() {
    Icon(Icons.Rounded.Search, contentDescription = "search icon")
}
