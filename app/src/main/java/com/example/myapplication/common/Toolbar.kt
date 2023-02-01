package com.example.myapplication.common

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import com.example.myapplication.common.extension.layoutInflater
import com.example.myapplication.databinding.ToolbarLayoutBinding

class Toolbar @JvmOverloads
constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) :
    LinearLayout(context, attrs, defStyleAttr){

        private val binding : ToolbarLayoutBinding by lazy {
            ToolbarLayoutBinding.inflate(context.layoutInflater(), this,false)
        }

    fun setup(){

    }

}