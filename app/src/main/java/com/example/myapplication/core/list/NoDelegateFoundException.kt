package com.example.myapplication.core.list

/**
 * Created by Rishabh on 27/09/21.
 */

class NoDelegateFoundException(private val item: Int, private val errorClass: String) : Exception() {
    override val message: String?
        get() = "No delegate found for the view type : $item in $errorClass"
}