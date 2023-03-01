package com.example.myapplication.ui.dashboard.addProduct

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.ShowimageItemLayoutBinding

class ShowImgAdapter(
    var context: Context,
    var arrayList: ArrayList<AddImageItem>,
    var listener: OnImageEditClick
) : RecyclerView.Adapter<ShowImgAdapter.ViewHolder>() {
    inner class ViewHolder (var binding: ShowimageItemLayoutBinding) : RecyclerView.ViewHolder(binding.root){
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ShowimageItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var item= arrayList[position]
       holder.binding.imgVwShowimg.setImageURI(item.image)
        holder.binding.rlEditProd.setOnClickListener {
            listener.onItemClick(position, true)
        }
    }
     interface OnImageEditClick{
         fun onItemClick(position: Int, wantToEdit : Boolean)
     }

}
