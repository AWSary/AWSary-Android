package com.lzcalderaro.awsary.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lzcalderaro.awsary.repository.AwsServicesRepositoryImp
import com.lzcalderaro.awsary.webservice.dto.AwsItem
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class HomeScreenViewModel(private val awsServicesRepository: AwsServicesRepositoryImp): ViewModel() {

    private val _filteredList = MutableStateFlow<List<AwsItem>?>(emptyList())
    private val _completeList = MutableLiveData<List<AwsItem>?>()

    val completeList: LiveData<List<AwsItem>?> = _completeList
    val filteredList: Flow<List<AwsItem>?> = _filteredList

    fun onValueChange(value: String) {
        viewModelScope.launch {

            if (value.isEmpty()) {
                resetListState()
                return@launch
            }

            val filter = _completeList.value?.filter {
                it.name.lowercase().contains(value)
            }

            _filteredList.emit(filter)
        }
    }

    init {
        viewModelScope.launch(IO) {
            awsServicesRepository.fetchList().collect {
                loadData(it)
            }
        }
    }

    private fun resetListState() {
        viewModelScope.launch {
            _filteredList.emit(_completeList.value)
        }
    }

    private fun loadData(list: List<AwsItem>?) {
        viewModelScope.launch {
            _filteredList.emit(list)
            _completeList.postValue(list)
        }
    }
}
