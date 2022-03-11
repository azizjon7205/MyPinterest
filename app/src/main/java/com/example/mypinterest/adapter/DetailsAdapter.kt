package com.example.mypinterest.adapter

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.bumptech.glide.Glide
import com.example.mypinterest.R
import com.example.mypinterest.model.Photo
import com.example.mypinterest.model.RelatedPhotos
import com.example.mypinterest.network.RetrofitHttp
import com.google.android.material.imageview.ShapeableImageView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailsAdapter(val context: Context, var items: ArrayList<Photo>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_details, parent, false)
        return PhotoViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is PhotoViewHolder)
            holder.bind(position)
    }

    override fun getItemCount() = items.size

    inner class PhotoViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val iv_photo: ShapeableImageView = view.findViewById(R.id.iv_photo)
        val iv_profile: ShapeableImageView = view.findViewById(R.id.iv_profile)
        val tv_fullName: TextView = view.findViewById(R.id.tv_fullName)
        val tv_followers: TextView = view.findViewById(R.id.tv_followers)
        val tv_follow: TextView = view.findViewById(R.id.tv_follow)

        val iv_settings_more: ImageView = view.findViewById(R.id.iv_more_settings)

        val recyclerView: RecyclerView = view.findViewById(R.id.rv_details_like_more)
        init {
            recyclerView.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        }


        fun bind(position: Int) {
            val photo = items[position]
//            iv_photo.setBackgroundColor(Color.parseColor("#262626"))
            iv_photo.setBackgroundColor(Color.parseColor(photo.color))
            if (photo.urls != null)
            Glide
                .with(view)
                .load(photo.urls?.small)
                .into(iv_photo)

            val user = photo.user!!
            if (user.profile_image != null)
                Glide.with(view)
                    .load(user.profile_image.small)
                    .error(R.drawable.ic_launcher_background)
                    .into(iv_profile)
            tv_fullName.text = user.name!!
            //tv_followers.text = user.links.followers

            getRelatedPhotos(photo.id!!)

        }

        fun refreshAdapter(items: ArrayList<Photo>){
            val adapter = PhotosAdapter(context, items)
            recyclerView.adapter = adapter
        }

        fun getRelatedPhotos(id: String){
            RetrofitHttp.photosService.getRelatedPhotos(id).enqueue(object : Callback<RelatedPhotos>{
                override fun onResponse(
                    call: Call<RelatedPhotos>,
                    response: Response<RelatedPhotos>,
                ) {
                    if (response.isSuccessful){
                        refreshAdapter(response.body()!!.results!!)
                    }
                }

                override fun onFailure(call: Call<RelatedPhotos>, t: Throwable) {

                }

            })
        }
    }
}