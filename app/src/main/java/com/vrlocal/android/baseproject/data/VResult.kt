package com.vrlocal.android.baseproject.data

/**
 * A generic class that holds a value with its loading status.
 *
 * VResult is usually created by the Repository classes where they return
 * `LiveData<VResult<T>>` to pass back the latest data to the UI with its fetch status.
 */

data class VResult<out T>(val status: Status, val data: T?, val message: String?) {

    enum class Status {
        NOT_AUTHENTICATED,
        SUCCESS,
        ERROR,
        LOADING,
        AUTHENTICATED
    }

    companion object {
        fun <T> success(data: T): VResult<T> {
            return VResult(Status.SUCCESS, data, null)
        }

        fun <T> error(message: String, data: T? = null): VResult<T> {
            return VResult(Status.ERROR, data, message)
        }

        fun <T> loading(data: T? = null): VResult<T> {
            return VResult(Status.LOADING, data, null)
        }


        fun <T> logout(): VResult<T> {
            return VResult<T>(Status.NOT_AUTHENTICATED, null, null)
        }


        fun <T> authenticated(data: T?): VResult<T> {
            return VResult<T>(Status.AUTHENTICATED, data, null)
        }
    }
}