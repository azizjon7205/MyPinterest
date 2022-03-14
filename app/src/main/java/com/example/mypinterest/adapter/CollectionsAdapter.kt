package com.example.mypinterest.adapter

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mypinterest.R
import com.example.mypinterest.activity.MainActivity
import com.example.mypinterest.fragments.DetailsFragment
import com.example.mypinterest.model.Collection
import com.example.mypinterest.model.Photo
import com.example.mypinterest.utils.Dialogs
import com.example.mypinterest.utils.Logger
import com.google.android.material.imageview.ShapeableImageView

class CollectionsAdapter(val context: Context, ) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
     var items: ArrayList<Collection> = ArrayList()

    @JvmName("setItems1")
    @SuppressLint("NotifyDataSetChanged")
    fun setItems(items: ArrayList<Collection>){
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_updates, parent, false)
        return PhotoViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is PhotoViewHolder)
            holder.bind(position)
    }

    override fun getItemCount() = items.size

    inner class PhotoViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val iv_profile: ImageView = view.findViewById(R.id.iv_profile)
        val tv_title: TextView = view.findViewById(R.id.tv_title)
        val tv_description: TextView = view.findViewById(R.id.tv_description)
        val iv_preview_1: ShapeableImageView = view.findViewById(R.id.iv_preview_1)
        val iv_preview_2: ShapeableImageView = view.findViewById(R.id.iv_preview_2)
        val iv_preview_3: ShapeableImageView = view.findViewById(R.id.iv_preview_3)
        val iv_preview_4: ShapeableImageView = view.findViewById(R.id.iv_preview_4)

        @SuppressLint("NotifyDataSetChanged")
        fun bind(position: Int) {

            val collection = items[position]
            Glide
                .with(view)
                .load(collection.user!!.profile_image!!.medium)
                .into(iv_profile)

            tv_title.text = collection.title
            tv_description.text = collection.description

            Glide.with(view).load(collection.preview_photos!![0].urls!!.small).into(iv_preview_1)
            Glide.with(view).load(collection.preview_photos[1].urls!!.small).into(iv_preview_2)
            Glide.with(view).load(collection.preview_photos[2].urls!!.small).into(iv_preview_3)
            Glide.with(view).load(collection.preview_photos[3].urls!!.small).into(iv_preview_4)

        }
    }
}