package com.meli.challenge.utils

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.map
import kotlinx.coroutines.Dispatchers

/**
 * Function to perform a get operation to
 * @see com.meli.challenge.api.ApiRepository
 */
//TODO connect whit Room to save last search
fun <T> performGetOperation(
    networkCall: suspend () -> Resource<T>
    ) : LiveData<Resource<T>> =
    liveData(Dispatchers.IO) {

        emit(Resource.loading())
        val responseStatus = networkCall.invoke()
        if (responseStatus.status == Resource.Status.SUCCESS)
            Log.i("Get Operarion","Response from server")
            emit(responseStatus)

        if (responseStatus.status == Resource.Status.ERROR){
            emit(Resource.error(responseStatus.message!!))
            Log.i("Get Operarion","Response from server: ${responseStatus.message}")
        }



    }