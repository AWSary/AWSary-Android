package com.lzcalderaro.awsary.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.lzcalderaro.awsary.repository.AwsServicesRepository
import com.lzcalderaro.awsary.webservice.dto.AwsList

class AwsServicesViewModel(private val awsServicesRepository: AwsServicesRepository): ViewModel() {

    fun getAwsServices(): LiveData<AwsList?> = liveData {
        val data = awsServicesRepository.loadList().value

        if (data != null) {
            emit(data)
        }
    }
}
