package com.lzcalderaro.awsary.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.lzcalderaro.awsary.repository.AwsServicesRepository
import com.lzcalderaro.awsary.webservice.dto.AwsItem

class AwsServicesViewModel(private val awsServicesRepository: AwsServicesRepository): ViewModel() {

    var awsList: List<AwsItem>? = null
    var selectedItem: AwsItem? = null

    fun getAwsServices(): LiveData<List<AwsItem>?> = liveData {
        val data = awsServicesRepository.loadList().value
        emit(data)
    }
}
