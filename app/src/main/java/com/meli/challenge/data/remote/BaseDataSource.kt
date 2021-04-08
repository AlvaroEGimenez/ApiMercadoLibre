package com.meli.challenge.data.remote


import android.util.Log
import com.meli.challenge.utils.Resource
import retrofit2.Response

abstract class BaseDataSource {

    protected suspend fun <T> getResult(call: suspend () -> Response<T>): Resource<T> {
        try {
            val response = call()
            if (response.isSuccessful) {
                val body = response.body()
                if (body != null) return Resource.success(body)
            }
            return error(" ${response.code()} ${response.message()}")
        } catch (e: Exception) {
            return error(e.message ?: e.toString())
        }
    }

    private fun <T> error(message: String): Resource<T> {
        Log.d("Remote",message)
        return Resource.error("Verificiar la conexion a internet, la busqueda fallo con el " +
                "siguiente mensaje: $message")
    }

}