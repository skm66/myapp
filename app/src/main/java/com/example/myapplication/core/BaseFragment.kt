package com.example.myapplication.core

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewStub
import android.widget.ProgressBar
import androidx.annotation.CallSuper
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.example.myapplication.R
import com.example.myapplication.common.appController.AppControllerContract
import com.example.myapplication.common.error.StandardError

typealias Inflate<T> = (View) -> T
abstract class BaseFragment<VB : ViewBinding>(private val inflate: Inflate<VB>): Fragment() {

    private var hasInflated = false
    private var mViewStub: ViewStub? = null
    private var visible = false
    var isPageLoaded = false
    protected val appController: AppControllerContract = AppControllerContract.get()


    private var _binding: VB? = null
    val binding get() = _binding
    protected lateinit var baseActivity: BaseActivity

    override fun onAttach(context: Context) {
        super.onAttach(context)
        baseActivity = context as BaseActivity
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bindBundle()
        val view = inflater.inflate(R.layout.base_fragment_view_stub_layout, container, false)

        mViewStub = view.findViewById(R.id.fragmentViewStub)
        mViewStub?.layoutResource = getLayout()

        if (visible && !hasInflated) {
            val inflatedView = mViewStub?.inflate()
            if(inflatedView != null) {
                injectBinding(inflatedView)
            }
            afterViewStubInflated(view)
        }

        return view
    }

    override fun onPause() {
        super.onPause()
        visible = false
        onPageInVisible()
    }

    override fun onResume() {
        super.onResume()

        visible = true
        if (mViewStub != null && !hasInflated) {
            val inflatedView = mViewStub?.inflate()
            if(inflatedView != null) {
                injectBinding(inflatedView)
                onViewCreated()
            }

            afterViewStubInflated(view)
        }

        if(!isPageLoaded) {
            onPageVisible()
            isPageLoaded = !canReloadPage()
        }
    }

    private fun injectBinding(inflatedView: View) {
        _binding = inflate.invoke(inflatedView)
    }


    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
        hasInflated = false
    }

    override fun onDetach() {
        super.onDetach()
        hasInflated = false
    }

    open fun onPageVisible() {
        Log.e("BaseFragment==", "${javaClass.simpleName} isVisible")
    }
    open fun onPageInVisible() {
        Log.e("BaseFragment==", "${javaClass.simpleName} isNotVisible")
    }

    open fun onViewCreated() {}
    open fun bindBundle(){}

    open fun onPageLoading() {}
    open fun onPageLoaded() {}
    open fun onPageError(error: StandardError) {}

    open fun canReloadPage(): Boolean = false

    @LayoutRes
    protected abstract fun getLayout(): Int

    @CallSuper
    protected fun afterViewStubInflated(originalViewContainerWithViewStub: View?) {
        hasInflated = true
        if (originalViewContainerWithViewStub != null) {
            val pb = originalViewContainerWithViewStub.findViewById<ProgressBar>(R.id.inflateProgressbar)
            pb.visibility = View.GONE
        }
    }
}