package com.example.mypinterest.fragments

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.bumptech.glide.Glide
import com.example.mypinterest.R
import com.example.mypinterest.activity.MainActivity
import com.example.mypinterest.adapter.PhotosAdapter
import com.example.mypinterest.managers.RoomManager
import com.example.mypinterest.model.Photo
import com.example.mypinterest.model.Pin
import com.example.mypinterest.model.Profile
import com.example.mypinterest.network.RetrofitHttp
import com.example.mypinterest.utils.Logger
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.google.android.material.imageview.ShapeableImageView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Math.abs


class ProfileFragment : Fragment() {


    var receivedResponseAtMillis: Long = 0
    var sentRequestAtMillis: Long = 0

    var myProfile: Profile? = null

    private lateinit var collapsingTBR: CollapsingToolbarLayout
    private lateinit var fm_inToolbar: FrameLayout
    private lateinit var ll_inToolbar: LinearLayout
    private lateinit var appBarLayout: AppBarLayout
    private lateinit var rv_savedPhotos: RecyclerView
    private lateinit var photosAdapter: PhotosAdapter

    // profile data
    private lateinit var iv_profile: ShapeableImageView
    private lateinit var iv_profile_inToolbar: ShapeableImageView
    private lateinit var tv_fullname: TextView
    private lateinit var tv_fullname_inToolbar: TextView
    private lateinit var tv_username: TextView
    private lateinit var tv_followers_count: TextView
    private lateinit var tv_followings_count: TextView

    override fun onAttach(context: Context) {
        super.onAttach(context)
//        getMyProfile()

        Logger.d("LifeCircle", "onAttach")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        getMyProfile()
        Logger.d("Profile", myProfile.toString())
        Logger.d("LifeCircle", "onCreate")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_profile, container, false)
        initViews(view)
        Logger.d("LifeCircle", "onCreateView")
        return view
    }

    private fun initViews(view: View) {
        collapsingTBR = view.findViewById(R.id.collapsingTBR)
        fm_inToolbar = view.findViewById(R.id.fm_inToolbar)
        ll_inToolbar = view.findViewById(R.id.ll_inToolbar)
        appBarLayout = view.findViewById(R.id.appBar)

        appBarLayout.addOnOffsetChangedListener(AppBarLayout.OnOffsetChangedListener { appBarLayout, verticalOffset ->
            if (abs(verticalOffset) - appBarLayout.totalScrollRange == 0) {
                // Collapsed
                fm_inToolbar.visibility = View.VISIBLE
                ll_inToolbar.visibility = View.INVISIBLE
            } else if (verticalOffset == 0) {
                // Expanded
                fm_inToolbar.visibility = View.INVISIBLE
                ll_inToolbar.visibility = View.VISIBLE
            } else {
                // Idle
                fm_inToolbar.visibility = View.INVISIBLE
                ll_inToolbar.visibility = View.VISIBLE
            }
        }
        )

        iv_profile = view.findViewById(R.id.iv_profile)
        iv_profile_inToolbar= view.findViewById(R.id.iv_profile_inToolbar)
        tv_fullname= view.findViewById(R.id.tv_profile_name)
        tv_fullname_inToolbar= view.findViewById(R.id.tv_fullName_inToolbar)
        tv_username= view.findViewById(R.id.tv_profile_username)
        tv_followers_count= view.findViewById(R.id.tv_profile_followers)
        tv_followings_count= view.findViewById(R.id.tv_profile_followings)


        rv_savedPhotos = view.findViewById(R.id.rv_savedPhotos)
        rv_savedPhotos.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)

        refreshAdapter(RoomManager.instance!!.pinDao().getPhotos() as ArrayList<Pin>)
    }

    private fun refreshAdapter(items: ArrayList<Pin>){

        val photos = ArrayList<Photo>()
        for (item in items){
            photos.add(item.photo)
        }
        photosAdapter = PhotosAdapter(requireContext(), this)
        photosAdapter.items.clear()
        photosAdapter.items.addAll(photos)
        rv_savedPhotos.adapter = photosAdapter
    }

    private fun getMyProfile(){
        RetrofitHttp.photosService.getUser().enqueue(object: Callback<Profile>{
            @SuppressLint("ResourceType", "SetTextI18n")
            override fun onResponse(call: Call<Profile>, response: Response<Profile>) {
                receivedResponseAtMillis = response.raw().receivedResponseAtMillis()
                sentRequestAtMillis = response.raw().sentRequestAtMillis()

                myProfile = response.body()!!
                Logger.d("MyProfile", "${response.body()}")
                Logger.d("MyProfile", "${receivedResponseAtMillis - sentRequestAtMillis}")

                Glide.with(requireContext()).load(myProfile!!.profile_image!!.medium)
                    .placeholder(Color.DKGRAY)
                    .into(iv_profile)
                Glide.with(requireContext()).load(myProfile!!.profile_image!!.medium)
                    .placeholder(Color.DKGRAY)
                    .into(iv_profile_inToolbar)
                tv_fullname.text = myProfile!!.name
                tv_fullname_inToolbar.text = myProfile!!.name
                tv_username.text = myProfile!!.username
                tv_followers_count.text = "${myProfile!!.followers_count} followers"
                tv_followings_count.text = "${myProfile!!.following_count} following"
            }

            override fun onFailure(call: Call<Profile>, t: Throwable) {
                Logger.d("MyProfile", t.localizedMessage!!)
            }

        })
    }

    override fun onResume() {
        super.onResume()
        (requireContext() as MainActivity).showBottomNavigation()
        (requireContext() as MainActivity).setLightStatusBar()
    }

}