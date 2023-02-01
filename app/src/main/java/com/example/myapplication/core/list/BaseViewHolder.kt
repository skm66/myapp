package com.example.myapplication.core.list

import android.view.View
import androidx.recyclerview.widget.RecyclerView

/**
 * Created by Rishabh on 27/09/21.
 */

abstract class BaseViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    abstract fun bindView(item: RecyclerViewListItem)

    open fun onDestroy(){}
}