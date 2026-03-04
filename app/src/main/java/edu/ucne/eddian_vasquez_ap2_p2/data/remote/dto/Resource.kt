package edu.ucne.eddian_vasquez_ap2_p2.data.remote.dt



import android.os.Message

sealed class Resource<T>(
    val data: T? = null,
    val message: String? = null

){
    class Success<T>(data: T) : Resource<T>(data)
    class Error<T>(message: String) : Resource<T>(null,message)
    class Loading<T> : Resource<T>()

}