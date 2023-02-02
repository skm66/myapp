package com.example.myapplication.ui.dashboard

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.DashboardItemLayoutBinding

class DashboardAdapter(var context: Context,
                       var arrList: ArrayList<DashboardItem>) : RecyclerView.Adapter<DashboardAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = DashboardItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return arrList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var item = arrList[position]
        holder.binding.ivSaree.setImageResource(item.image)
        holder.binding.tvPrice.text = item.price.toString()
    }


    inner class ViewHolder(var binding : DashboardItemLayoutBinding) : RecyclerView.ViewHolder(binding.root)
}