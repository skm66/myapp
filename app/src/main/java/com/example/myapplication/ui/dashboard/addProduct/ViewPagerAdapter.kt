package com.example.myapplication.ui.dashboard.addProduct

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.viewpager.widget.PagerAdapter
import com.example.myapplication.R
import java.util.*
import kotlin.collections.ArrayList

class ViewPagerAdapter(val context: Context, val imgList: ArrayList<AddImageItem>?) : PagerAdapter() {
    override fun getCount(): Int {
        return imgList!!.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object` as ConstraintLayout
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val item = imgList?.get(position)

        val layoutinflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

        val itemView : View = layoutinflater.inflate(R.layout.item_image_slider, container, false)

        val imageView : ImageView = itemView.findViewById<View>(R.id.imgVw_slideImage) as ImageView
        imageView.setImageURI(item?.image)

        Objects.requireNonNull(container).addView(itemView)

        // on below line we are simply
        // returning our item view.
        return itemView
        //return super.instantiateItem(container, position)
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        //super.destroyItem(container, position, `object`)


        container.removeView(`object` as ConstraintLayout)
    }
}