package com.example.myapplication.common.appController

import android.content.Context
import androidx.lifecycle.MutableLiveData

interface AppControllerContract {

    companion object {
        fun get(): AppControllerContract = AppController.get()
    }

    val properContext: Context


}