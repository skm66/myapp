package com.example.myapplication.core.list


/**
 * Created by Rishabh on 27/09/21.
 */

interface RecyclerViewListItem {
    fun getViewType(): Int
    fun getUnique(): Any
}