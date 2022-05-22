package com.example.mypinterest.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mypinterest.R
import com.example.mypinterest.model.Photo
import com.example.mypinterest.utils.Dialogs
import com.example.mypinterest.utils.Extensions
import com.example.mypinterest.utils.Logger

class PhotosAdapter(val context: Context, val fragment: Fragment) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var items: ArrayList<Photo> = ArrayList()

    @JvmName("setItems1")
    @SuppressLint("NotifyDataSetChanged")
    fun setItems(items: ArrayList<Photo>) {
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_photos, parent, false)
        return PhotoViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is PhotoViewHolder)
            holder.bind(position)
    }

    override fun getItemCount() = items.size

    inner class PhotoViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
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
                .load(photo.urls!!.small)
                .into(iv_photo)

            tv_photo_description.text = photo.description

            iv_photo.setOnClickListener {
                Logger.d("ClickedPosition", "$position")
                fragment.findNavController()
                    .navigate(R.id.detailsFragment,
                        bundleOf("photos" to Extensions.parseToString(items),
                            "position" to position))
            }

            iv_settings_more.setOnClickListener {
                Dialogs.showBottomSheetDialog(context, photo)
            }
        }
    }
}