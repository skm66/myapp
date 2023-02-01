package com.example.myapplication.core

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.FrameLayout
import androidx.coordinatorlayout.widget.CoordinatorLayout
import com.example.myapplication.R
import com.example.myapplication.common.Toolbar
import com.example.myapplication.common.error.NO_INTERNET_ERROR
import com.example.myapplication.common.error.StandardError
import com.example.myapplication.common.extension.displayError
import com.example.myapplication.common.extension.isConnected
import com.example.myapplication.common.internet.ConnectionAware

abstract class BaseActivity : AppCompatActivity(), ConnectionAware  {

    private var toolbar: Toolbar? = null
    private var isConnectionAware: Boolean = false

    override fun setContentView(view: View?) {
        val coordinatorLayout: CoordinatorLayout =
            layoutInflater.inflate(R.layout.activity_base, null) as CoordinatorLayout
        val activityContainer: FrameLayout =
            coordinatorLayout.findViewById(R.id.base_layout_container)
        toolbar = coordinatorLayout.findViewById(R.id.base_toolbar)
        toolbar?.visibility = if (showToolbar()) View.VISIBLE else View.GONE
        activityContainer.addView(view)

        super.setContentView(view)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base)
    }

    open fun showToolbar(): Boolean = true

    open fun onPageLoading() {}

    open fun onPageLoaded() {}

    open fun onPageFailed(error: StandardError) {}

    private fun observeInternetConnection() {
        isConnectionAware = true
    }

    override fun onDisconnected() {
        if (isConnectionAware) {
            if (!isConnected) {
                displayError(NO_INTERNET_ERROR)
            }
        }
    }

    override fun onConnected() {}
}