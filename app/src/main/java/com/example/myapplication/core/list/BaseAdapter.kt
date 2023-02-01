package com.example.myapplication.core.list

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

abstract class BaseAdapter : RecyclerView.Adapter<BaseViewHolder>() {

    private var items: MutableList<RecyclerViewListItem> = mutableListOf()
    private var viewGroup: ViewGroup? = null
    protected var delegates = HashMap<Int, DelegateInterface>()

    override fun getItemCount(): Int {
        return items.size
    }

    fun getItemAt(position: Int): RecyclerViewListItem {
        return items[position]
    }

    fun updateData(newItems: MutableList<RecyclerViewListItem>?, useDiffUtil: Boolean = true) {
        if (useDiffUtil) {
            newItems?.let {
                val diffResult = DiffUtil.calculateDiff(BaseDiffUtils(this.items, it))
                this.items.clear()
                this.items.addAll(newItems)
                diffResult.dispatchUpdatesTo(this)
            }
        }else {
            newItems?.let {
                this.items.clear()
                this.items.addAll(newItems)
            }
            notifyDataSetChanged()
        }
    }

    fun updateItem(index: Int, newItems: MutableList<RecyclerViewListItem>?) {
        newItems?.let {
            this.items.clear()
            this.items.addAll(newItems)
        }
        notifyItemChanged(index)
    }

    //abstract fun setData(items: MutableList<RecyclerViewListItem>)

    open fun initDelegates() {

    }

    override fun getItemViewType(position: Int) = items[position].getViewType()

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        delegates[getItemViewType(position)]?.onBindViewHolder(holder, items[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        viewGroup = parent
        return try {
            parent.clearFocus()
            viewGroup?.clearFocus()
            delegates[viewType]?.onCreateViewHolder(parent)!!
        } catch (e: Exception) {
            e.printStackTrace()
            throw NoDelegateFoundException(viewType, this::class.java.simpleName)
        }
    }
}