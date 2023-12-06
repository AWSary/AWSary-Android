package com.lzcalderaro.awsary.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lzcalderaro.awsary.repository.AwsServicesRepositoryImp
import com.lzcalderaro.awsary.webservice.dto.AwsItem
import kotlinx.coroutines.launch

class DetailScreenViewModel(private val awsServicesRepository: AwsServicesRepositoryImp): ViewModel() {

    private val _awsItem = MutableLiveData<AwsItem>()
    val awsItem: LiveData<AwsItem> = _awsItem

    fun loadItem(key: String) {
        viewModelScope.launch {
            awsServicesRepository.fetchItem(key).collect {
                _awsItem.postValue(it)
            }
        }
    }
}