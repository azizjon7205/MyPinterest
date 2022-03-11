package com.example.mypinterest.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.mypinterest.R
import com.example.mypinterest.activity.MainActivity
import com.example.mypinterest.adapter.PhotosAdapter
import com.example.mypinterest.model.Photo
import com.example.mypinterest.network.RetrofitHttp
import com.example.mypinterest.utils.Logger
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class PhotosFragment private constructor(): Fragment() {

    companion object{
        @SuppressLint("StaticFieldLeak")
        private var fragment: PhotosFragment? = null

        fun newInstance(): PhotosFragment?{
            if (fragment == null){
                fragment = PhotosFragment()
            }
            return fragment
        }
    }

    private lateinit var recyclerPhotos: RecyclerView
    private lateinit var adapter: PhotosAdapter
    private lateinit var fm_loading: FrameLayout

    private var loatingCount = 1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_photos, container, false)
        initViews(view)
        (requireContext() as MainActivity).showBottomNavigation()

        Logger.d("LifeCircle", "onCreateView")
        return view
    }

    private fun initViews(view: View) {
        fm_loading = view.findViewById(R.id.fm_loading)
        recyclerPhotos = view.findViewById(R.id.rv_photos)
        val layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
//        layoutManager.gapStrategy = StaggeredGridLayoutManager.GAP_HANDLING_NONE
        recyclerPhotos.layoutManager = layoutManager

        getPhotos(1)

        recyclerPhotos.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                val layoutManager =
                    StaggeredGridLayoutManager::class.java.cast(recyclerPhotos.layoutManager)
                val totalItemCount = layoutManager!!.itemCount
                var lastVisible = intArrayOf(0, 0)
                lastVisible = layoutManager.findLastVisibleItemPositions(lastVisible)
                val spanCount = layoutManager.spanCount
                Logger.d("@@@", "PhotosList -> ${lastVisible[0]} $spanCount")
                if (lastVisible[0] == totalItemCount - 2 || lastVisible[1] == totalItemCount - 1)
                    getPhotos(++loatingCount)

            }
        })
    }

    private fun refreshAdapter(items: ArrayList<Photo>) {
        adapter = PhotosAdapter(requireContext(), items)
        recyclerPhotos.adapter = adapter
    }

    private fun getPhotos(page: Int) {
        RetrofitHttp.photosService.listPhotos(page = page)
            .enqueue(object : Callback<ArrayList<Photo>> {
                override fun onResponse(
                    call: Call<ArrayList<Photo>>,
                    response: Response<ArrayList<Photo>>,
                ) {
//                    refreshAdapter(response.body()!!)
                    fm_loading.visibility = View.INVISIBLE
                    if (page == 1) refreshAdapter(response.body()!!)
                    else {
                        Logger.d("@@@", "LoadMore -> ")
                        adapter.items.addAll(response.body()!!)
                        adapter.notifyDataSetChanged()
                    }
                    Logger.d("@@@", "PhotosList -> ${response.body()}")
                }

                override fun onFailure(call: Call<ArrayList<Photo>>, t: Throwable) {
                    Logger.d("@@@", "PhotosList -> ${t.localizedMessage}")
                }

            })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Logger.d("LifeCircle", "onCreate")
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Logger.d("LifeCircle", "onViewCreated")
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        Logger.d("LifeCircle", "onViewStateRestored")
    }

    override fun onStart() {
        super.onStart()
        Logger.d("LifeCircle", "onStart")
    }

    override fun onResume() {
        super.onResume()
        Logger.d("LifeCircle", "onResume")
    }

    override fun onPause() {
        super.onPause()
        Logger.d("LifeCircle", "onPause")
    }

    override fun onStop() {
        super.onStop()
        Logger.d("LifeCircle", "onStop")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Logger.d("LifeCircle", "onSaveInstanceState")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Logger.d("LifeCircle", "onDestroyView")
    }

    override fun onDestroy() {
        super.onDestroy()
        Logger.d("LifeCircle", "onDestroy")
    }

}