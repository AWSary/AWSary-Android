package com.lzcalderaro.awsary.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.lzcalderaro.awsary.webservice.Service
import com.lzcalderaro.awsary.webservice.ServiceImp
import com.lzcalderaro.awsary.webservice.dto.AwsItem
import java.lang.Exception

class AwsServicesRepository {

    private val client: ServiceImp = Service.create()
    private val awsList = MutableLiveData<List<AwsItem>?>()

    suspend fun loadList(): LiveData<List<AwsItem>?> {
        return try {

            val getList = client.getList()

            Log.d("AWSARYDEBUG", getList.toString())

            awsList.value = client.getList()
            awsList
        } catch (e:Exception) {
            awsList
        }
    }
}