package com.vrlocal.android.baseproject.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.map
import com.vrlocal.android.baseproject.data.VResult.Status.ERROR
import com.vrlocal.android.baseproject.data.VResult.Status.SUCCESS
import kotlinx.coroutines.Dispatchers

/**
 * The database serves as the single source of truth.
 * Therefore UI can receive data updates from database only.
 * Function notify UI about:
 * [VResult.Status.SUCCESS] - with data from database
 * [VResult.Status.ERROR] - if error has occurred from any source
 * [VResult.Status.LOADING]
 */
fun <T, A> resultLiveData(
    databaseQuery: () -> LiveData<T>,
    networkCall: suspend () -> VResult<A>,
    saveCallResult: suspend (A) -> Unit
): LiveData<VResult<T>> =
    liveData(Dispatchers.IO) {
        emit(VResult.loading<T>())
        val source = databaseQuery.invoke().map { VResult.success(it) }
        emitSource(source)

        val responseStatus = networkCall.invoke()
        if (responseStatus.status == SUCCESS) {
            saveCallResult(responseStatus.data!!)
        } else if (responseStatus.status == ERROR) {
            emit(VResult.error<T>(responseStatus.message!!))
            emitSource(source)
        }
    }

fun <T> databaseLiveData(databaseQuery: () -> LiveData<T>): LiveData<VResult<T>> =
    liveData(Dispatchers.IO) {
        emit(VResult.loading<T>())
        val source = databaseQuery.invoke().map {
            VResult.success(it)
        }
        emitSource(source)
    }


fun <A> networkLiveData(networkCall: suspend () -> VResult<A>): LiveData<VResult<A>> =
    liveData(Dispatchers.IO) {
        emit(VResult.loading<A>())

        val responseStatus = networkCall.invoke()
        if (responseStatus.status == SUCCESS) {
            emit(VResult.success<A>(responseStatus.data!!))
        } else if (responseStatus.status == ERROR) {
            emit(VResult.error<A>(responseStatus.message!!))
        }
    }
