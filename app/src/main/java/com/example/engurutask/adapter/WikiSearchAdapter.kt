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
import com.example.engurutask.ui.main.OnItemClickedListener
import com.example.engurutask.utility.Utils
import kotlinx.android.synthetic.main.item_all_list_item.view.*

class WikiSearchAdapter(private var context: Context?, private var listOfPages: ArrayList<Page>,private var onItemClickedListener: OnItemClickedListener) :
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
        if(listOfPages[holder.adapterPosition].thumbnail==null||listOfPages[holder.adapterPosition].thumbnail?.source.isNullOrEmpty()){
            try {
                Glide.with(context!!)
                    .asBitmap()
                    .apply(
                        RequestOptions().fitCenter().diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                            .placeholder(R.drawable.ic_placeholder).circleCrop()
                    )
                    .load(R.drawable.ic_placeholder)
                    .into(object : BitmapImageViewTarget(holder.itemView.ivSearchImage) {
                        override fun setResource(resource: Bitmap?) {
                            holder.itemView.ivSearchImage.setImageBitmap(resource)
                        }
                    })
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }else{
            try {

                Glide.with(context!!)
                    .asBitmap()
                    .apply(
                        RequestOptions().fitCenter().diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                            .placeholder(R.drawable.ic_placeholder).circleCrop()
                    )
                    .load(listOfPages[holder.adapterPosition].thumbnail?.source)
                    .into(object : BitmapImageViewTarget(holder.itemView.ivSearchImage) {
                        override fun setResource(resource: Bitmap?) {
                            holder.itemView.ivSearchImage.setImageBitmap(resource)
                        }
                    })
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }


        holder.itemView.setOnClickListener { onItemClickedListener.onItemClicked(Utils.getUrlFromTitle(listOfPages[position].title)) }

        holder.itemView.tvTitle.text=listOfPages[position].title

        holder.itemView.tvDescription.text =
            listOfPages[position].terms?.description?.get(0) ?: ""

    }

    inner class ViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView!!)

}