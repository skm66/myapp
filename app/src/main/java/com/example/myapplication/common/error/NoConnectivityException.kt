package com.example.myapplication.common.error

import java.io.IOException

const val NO_INTERNET_CONNECTION_CODE = 503

val NO_INTERNET_ERROR = NoConnectivityException.standardError

object NoConnectivityException : IOException() {
    override val message : String
        get() = "No Internet Connection"

    val standardError: StandardError = StandardError(
        responseCode = NO_INTERNET_CONNECTION_CODE,
        developerError = "No Internet!",
        displayError = "No Internet!",
        )
}