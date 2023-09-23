package com.lzcalderaro.awsary.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.lzcalderaro.awsary.webservice.Service
import com.lzcalderaro.awsary.webservice.ServiceImp
import com.lzcalderaro.awsary.webservice.dto.AwsList
import java.lang.Exception

class AwsServicesRepository {

    private val client: ServiceImp = Service.create()
    private val awsList = MutableLiveData<AwsList?>()

    suspend fun loadList(): LiveData<AwsList?> {
        return try {
            awsList.value = client.getList()
            awsList
        } catch (e:Exception) {
            awsList
        }
    }
}