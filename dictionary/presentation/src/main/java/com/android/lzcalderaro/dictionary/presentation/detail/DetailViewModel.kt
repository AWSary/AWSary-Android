package com.android.lzcalderaro.dictionary.presentation.detail

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.lzcalderaro.core.domain.util.Result
import com.android.lzcalderaro.core.domain.util.map
import com.android.lzcalderaro.core.presentation.ui.UiText
import com.android.lzcalderaro.dictionary.domain.DictionaryRepository
import com.android.lzcalderaro.dictionary.domain.ServiceLogoStorage
import com.android.lzcalderaro.dictionary.presentation.R
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class DetailViewModel(
    private val dictionaryRepository: DictionaryRepository,
    private val serviceLogoStorage: ServiceLogoStorage
): ViewModel() {

    var state by mutableStateOf(DetailState())
        private set

    private var eventChannel = Channel<DetailEvent>()
    val events = eventChannel.receiveAsFlow()

    fun onAction(action: DetailAction) {
        when(action) {
            is DetailAction.LoadItem -> fetchItem(action.id)
            else -> Unit
        }
    }

    private fun fetchItem(id: Int) {
        viewModelScope.launch {

            val isAlternative = serviceLogoStorage.get()?.alternative ?: false

            state = state.copy(isFetching = true)
            val result = dictionaryRepository.fetchItem(id)
            state = state.copy(isFetching = false)

            when(result) {
                is Result.Error -> {
                    eventChannel.send(
                        DetailEvent.Error(
                        UiText.StringResource(R.string.conextion_error)
                    ))
                }

                is Result.Success -> {
                    result.map {
                        state = state.copy(data = it.first(), isAlternative = isAlternative)
                    }
                }
            }
        }
    }

}