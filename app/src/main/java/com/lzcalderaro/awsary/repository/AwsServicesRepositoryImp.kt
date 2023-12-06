package com.lzcalderaro.awsary.repository

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.getValue
import com.lzcalderaro.awsary.webservice.dto.AwsItem
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

class AwsServicesRepositoryImp: AwsServicesRepository {

    private val databaseReference: DatabaseReference = FirebaseDatabase.getInstance().reference

    override fun fetchItem(position: String): Flow<AwsItem> = callbackFlow {

        val postListener = object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {

                    val item = snapshot.getValue<AwsItem>()

                    item.let {

                        if (it != null) {
                            trySend(it)
                        }
                    }
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        }
        databaseReference.child(position).addListenerForSingleValueEvent(postListener)
        awaitClose {
            databaseReference.removeEventListener(postListener)
        }
    }

    override fun fetchList(): Flow<List<AwsItem>> = callbackFlow {

        val postListener = object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    val servicesList: MutableList<AwsItem> = mutableListOf()

                    snapshot.children.forEach {

                        val item = it.getValue<AwsItem>()

                        item.let { awsItem ->
                            if (awsItem != null) {
                                servicesList.add(awsItem)
                            }
                        }
                    }

                    trySend(servicesList)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        }
        databaseReference.addListenerForSingleValueEvent(postListener)

        awaitClose {
            databaseReference.removeEventListener(postListener)
        }
    }
}