package com.example.myapplication.common.extension

import android.app.Activity
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.view.LayoutInflater
import android.widget.Toast
import com.example.myapplication.common.appController.AppControllerContract
import com.example.myapplication.common.error.StandardError

fun Context.layoutInflater(): LayoutInflater = LayoutInflater.from(this)


//for internet connectivity
val isConnected: Boolean
    get() {
        var result = false
        val connectivityManager =
            AppControllerContract.get().properContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val networkCapabilities = connectivityManager.activeNetwork ?: return false
            val actNw =
                connectivityManager.getNetworkCapabilities(networkCapabilities) ?: return false
            result = when {
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                else -> false
            }
        } else {
            connectivityManager.run {
                connectivityManager.activeNetworkInfo?.run {
                    result = when (type) {
                        ConnectivityManager.TYPE_WIFI -> true
                        ConnectivityManager.TYPE_MOBILE -> true
                        ConnectivityManager.TYPE_ETHERNET -> true
                        else -> false
                    }

                }
            }
        }

        return result
    }


fun Activity.displayError(standardError: StandardError) {
    if (standardError.displayError != null) {
        Toast.makeText(this, standardError.displayError, Toast.LENGTH_LONG).show()
        return
    }
}
