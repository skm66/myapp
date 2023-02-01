package com.example.myapplication.common.error


data class StandardError(
    val responseCode: Int? = null,
    val developerError: String = "",
    var displayError: String = "",
    val icon: Int? = null,
    val displayErrorId: Int? = null,
    var smallIcon: Int? = null
) : Throwable(developerError)
