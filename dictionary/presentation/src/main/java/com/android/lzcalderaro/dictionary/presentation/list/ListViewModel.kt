package com.android.lzcalderaro.dictionary.presentation.list

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.lzcalderaro.core.domain.util.Result
import com.android.lzcalderaro.core.domain.util.map
import com.android.lzcalderaro.core.presentation.ui.UiText
import com.android.lzcalderaro.dictionary.domain.AwsaryIconData
import com.android.lzcalderaro.dictionary.domain.DictionaryRepository
import com.android.lzcalderaro.dictionary.domain.ServiceLogoData
import com.android.lzcalderaro.dictionary.domain.ServiceLogoStorage
import com.android.lzcalderaro.dictionary.presentation.R
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class ListViewModel(
    private val listRepository: DictionaryRepository,
    private val serviceLogoStorage: ServiceLogoStorage
): ViewModel() {

    var state by mutableStateOf(ListState())
        private set

    private var eventChannel = Channel<ListEvent>()
    val events = eventChannel.receiveAsFlow()

    private lateinit var originalList: List<AwsaryIconData>
    private var isAlternative: Boolean = false

    init {
        isAlternative()
        getItems()
    }

    fun onAction(action: ListAction) {
        when(action) {
            is ListAction.Searching -> filterList(action.text)
            is ListAction.OnChangeServiceLogo -> changeServiceLogo(action.isAlternative)
            else -> Unit
        }
    }

    private fun changeServiceLogo(alternative: Boolean) {

        viewModelScope.launch {
            serviceLogoStorage.set(data = ServiceLogoData(
                alternative = alternative
            ))
            isAlternative = alternative
            state = state.copy(isAlternative = isAlternative)
        }
    }

    private fun isAlternative() {
        viewModelScope.launch {
            isAlternative = serviceLogoStorage.get()?.alternative ?: false
        }
    }

    private fun filterList(text: String) {

        if (text.isEmpty()) {
            state = state.copy(dictionaryData = originalList)
            return
        }

        val filteredList = originalList.filter { it.name.lowercase().contains(text) }
        state = state.copy(dictionaryData = filteredList, isAlternative = isAlternative)
    }

    private fun getItems() {
        viewModelScope.launch {

            state = state.copy(isFetching = true)
            val result = listRepository.fetchList()
            state = state.copy(isFetching = false)

            when(result) {
                is Result.Error -> {
                    eventChannel.send(ListEvent.Error(
                        UiText.StringResource(R.string.conextion_error)
                    ))
                }

                is Result.Success -> {
                    result.map {
                        originalList = it.sortedBy { it.name }
                        state = state.copy(dictionaryData = originalList, isAlternative = isAlternative)
                    }
                }
            }
        }
    }
}