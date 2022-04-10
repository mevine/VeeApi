package com.example.veeapi.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.veeapi.R
import com.example.veeapi.adapter.AdapterModel.RVHolder
import com.example.veeapi.databinding.ItemRecyclerviewBinding
import com.example.veeapi.responsemodel.DataAdapterList


class AdapterModel(var rvDataList: List<DataAdapterList>, context: Context) :
    RecyclerView.Adapter<RVHolder>() {

    var binding : ItemRecyclerviewBinding? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RVHolder {
        binding = ItemRecyclerviewBinding.inflate(LayoutInflater.from(parent.context))
        return RVHolder(binding!!.root)
    }

    override fun onBindViewHolder(holder: RVHolder, position: Int) {
        // set data to UI
        Glide.with(holder.largeImg.context)
            .load(rvDataList[position].largeImageURL)
            .override(400, 200)
            .apply(RequestOptions()
                .placeholder(R.drawable.baseline_photo_size_select_actual_black_48dp))
            .error(R.drawable.baseline_broken_image_red_700_48dp)
            .into(holder.largeImg)

        holder.viewsCount.text = rvDataList[position].views
        holder.userName.text = rvDataList[position].user
        holder.searchTags.text = rvDataList[position].tags
    }

    override fun getItemCount(): Int {
        return rvDataList.size
    }

    class RVHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var largeImg : ImageView
        var viewsCount : TextView
        var userName : TextView
        var searchTags : TextView

        init {
            val itemRecyclerviewBinding : ItemRecyclerviewBinding = ItemRecyclerviewBinding.bind(itemView)
            largeImg = itemRecyclerviewBinding.mainImage
            viewsCount = itemRecyclerviewBinding.numberOfViews
            userName = itemRecyclerviewBinding.userName
            searchTags = itemRecyclerviewBinding.searchTags
        }
    }
}


