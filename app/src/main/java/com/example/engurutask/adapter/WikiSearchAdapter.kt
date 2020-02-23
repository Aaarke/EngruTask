package com.example.engurutask.adapter

import android.content.Context
import android.graphics.Bitmap
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.BitmapImageViewTarget
import com.example.engurutask.R
import com.example.engurutask.model.Page
import kotlinx.android.synthetic.main.item_all_list_item.view.*

class WikiSearchAdapter(private var context: Context?, private var listOfPages: ArrayList<Page>) :
    RecyclerView.Adapter<WikiSearchAdapter.ViewHolder>() {

    fun updateAdapter(listOfPages: ArrayList<Page>) {
        this.listOfPages.clear()
        this.listOfPages = listOfPages
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_all_list_item, parent, false)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return listOfPages.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        try {
            Glide.with(context!!)
                .asBitmap()
                .apply(
                    RequestOptions().fitCenter().diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                        .placeholder(R.drawable.ic_placeholder).circleCrop()
                )
                .load(listOfPages[position].thumbnail.source)
                .into(object : BitmapImageViewTarget(holder.itemView.ivSearchImage) {
                    override fun setResource(resource: Bitmap?) {
                        holder.itemView.ivSearchImage.setImageBitmap(resource)
                    }
                })
        } catch (e: Exception) {
            e.printStackTrace()
        }

        holder.itemView.tvTitle.text=listOfPages[position].title

        holder.itemView.tvDescription.text =
            listOfPages[position].terms.description?.get(0).toString()

    }

    inner class ViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView!!)

}