package com.example.myapplication.core.list

import android.view.ViewGroup

/**
 * Created by Rishabh on 27/09/21.
 */

interface DelegateInterface {
    fun onCreateViewHolder(parent: ViewGroup): BaseViewHolder
    fun onBindViewHolder(holder: BaseViewHolder, item: RecyclerViewListItem)
}