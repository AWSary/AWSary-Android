package com.lzcalderaro.awsary.viewModels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.lzcalderaro.awsary.repository.AwsServicesRepository
import com.lzcalderaro.awsary.webservice.dto.AwsItem

class AwsServicesViewModel(private val awsServicesRepository: AwsServicesRepository): ViewModel() {

    fun getAwsServices(): LiveData<List<AwsItem>?> = liveData {
        val data = awsServicesRepository.loadList().value

        Log.d("AWSARYDEBUG", data.toString())

        if (data != null) {
            emit(data)
        }
    }
}
