package com.lzcalderaro.awsary.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lzcalderaro.awsary.repository.AwsServicesRepository
import com.lzcalderaro.awsary.webservice.dto.AwsItem
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class AwsServicesViewModel(private val awsServicesRepository: AwsServicesRepository): ViewModel() {

    var selectedItem: AwsItem? = null

    private val _filteredList = MutableLiveData<List<AwsItem>?>()
    private val _completedList = MutableLiveData<List<AwsItem>?>()

    val filteredList: LiveData<List<AwsItem>?> = _filteredList

    fun getAwsServices() {
        viewModelScope.launch(IO) {
            awsServicesRepository.loadListFromFirebase(::loadData)
        }
    }

    private fun loadData(list: List<AwsItem>?) {
        _filteredList.postValue(list)
        _completedList.postValue(list)
    }

    fun onSearch(value: String) {

        if (_completedList.value?.isEmpty() == true) {
            return
        }

        if (value.isEmpty()) {
            _filteredList.postValue(_completedList.value)
            return
        }

        val filter = _completedList.value?.filter {
            it.name.lowercase().contains(value)
        }

        _filteredList.postValue(filter)
    }
}
