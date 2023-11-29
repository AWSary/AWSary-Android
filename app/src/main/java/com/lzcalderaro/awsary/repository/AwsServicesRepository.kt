package com.lzcalderaro.awsary.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.getValue
import com.lzcalderaro.awsary.webservice.Service
import com.lzcalderaro.awsary.webservice.ServiceImp
import com.lzcalderaro.awsary.webservice.dto.AwsItem

class AwsServicesRepository {

    private val client: ServiceImp = Service.create()
    private val _awsList = MutableLiveData<List<AwsItem>?>()

    private val databaseReference: DatabaseReference = FirebaseDatabase.getInstance().reference


    suspend fun loadList(): LiveData<List<AwsItem>?> {
        return try {
            _awsList.value = client.getList()
            _awsList
        } catch (e:Exception) {
            _awsList
        }
    }

    fun loadListFromFirebase(callback: (List<AwsItem>?) -> Unit) {
        databaseReference.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if (dataSnapshot.exists()) {
                    val servicesList: MutableList<AwsItem> = mutableListOf()

                    dataSnapshot.children.forEach {

                        val item = it.getValue<AwsItem>()

                        item.let { serializedObject ->

                            if (serializedObject == null) {
                                Log.d("QUEROVERRRR", "NULO")
                                return@let
                            }

                            servicesList.add(serializedObject)
                        }
                    }
                    callback(servicesList)
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Handle error
            }
        })
    }
}