package com.example.myapplication.common.internet

import androidx.lifecycle.LifecycleOwner

/**
 * Created by Rishabh on 18/11/21.
 */

interface ConnectionAware : LifecycleOwner {
    fun onDisconnected()
    fun onConnected()
}