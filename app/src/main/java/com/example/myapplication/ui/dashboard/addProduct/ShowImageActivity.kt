package com.example.myapplication.ui.dashboard.addProduct

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myapplication.databinding.ActivityShowImageBinding

class ShowImageActivity : AppCompatActivity() {

    private val binding : ActivityShowImageBinding by lazy {
        ActivityShowImageBinding.inflate(layoutInflater)
    }

    var imgList: ArrayList<AddImageItem>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        intent?.let {
            imgList = it.getSerializableExtra("Image_to_be_send") as ArrayList<AddImageItem>?
        }
        binding.vwPagerShowAllImg.adapter = ViewPagerAdapter(this, imgList)
    }
}