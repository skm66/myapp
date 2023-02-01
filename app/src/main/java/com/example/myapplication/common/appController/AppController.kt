package com.example.myapplication.common.appController

import android.content.Context
import java.lang.ref.WeakReference

class AppController : AppControllerContract {

    companion object {
        private var INSTANCE: AppController? = null
        private var context: WeakReference<Context>? = null


        fun init(
            context: WeakReference<Context>,
        ): AppController? {

            INSTANCE = AppController()

            Companion.context = context
            return INSTANCE
        }

        fun get(): AppController =
            INSTANCE
                ?: throw AppContextGoneException
    }

    override val properContext: Context
        get() = context?.get()
            ?: throw AppContextGoneException

}