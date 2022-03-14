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
import com.example.mypinterest.database.MyPhoto
import com.example.mypinterest.fragments.DetailsFragment
import com.example.mypinterest.utils.Dialogs
import com.example.mypinterest.utils.Logger

class MyPhotosAdapter(val context: Context, ) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
     var items: ArrayList<MyPhoto> = ArrayList()

    @JvmName("setItems1")
    @SuppressLint("NotifyDataSetChanged")
    fun setItems(items: ArrayList<MyPhoto>){
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_photos, parent, false)
        return MyPhotoViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is MyPhotoViewHolder)
            holder.bind(position)
    }

    override fun getItemCount() = items.size

    inner class MyPhotoViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val iv_photo: ImageView = view.findViewById(R.id.iv_photo)
        val tv_photo_description: TextView = view.findViewById(R.id.tv_photo_description)
        val ll_photo_container: LinearLayout = view.findViewById(R.id.ll_photo_container)

        val iv_settings_more: ImageView = view.findViewById(R.id.iv_photo_more)

        @SuppressLint("NotifyDataSetChanged")
        fun bind(position: Int) {

            val photo = items[position]
            iv_photo.setBackgroundColor(Color.parseColor(photo.color))
            Glide
                .with(view)
                .load(photo.urls)
                .into(iv_photo)

            tv_photo_description.text = photo.description

            iv_photo.setOnClickListener {
                Logger.d("ClickedPosition", "$position")
//                (context as MainActivity).replaceFragment(DetailsFragment(items, position))
            }

            iv_settings_more.setOnClickListener {
//                Dialogs.showBottomSheetDialog(context, photo)
            }
        }
    }
}