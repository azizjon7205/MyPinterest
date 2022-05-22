package com.example.mypinterest.fragments

import android.content.Context
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.activity.OnBackPressedCallback
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.mypinterest.R
import com.example.mypinterest.activity.MainActivity
import com.example.mypinterest.adapter.DetailsAdapter
import com.example.mypinterest.model.Photo
import com.example.mypinterest.utils.Extensions
import com.example.mypinterest.utils.Logger

class DetailsFragment() : Fragment() {

//    companion object{
//        private var fragment: DetailsFragment? = null
//
//        fun newInstance(items: ArrayList<Photo>, position: Int): DetailsFragment?{
//            if (fragment == null){
//                fragment = DetailsFragment(items, position)
//            }
//            return fragment
//        }
//    }
    private lateinit var items: ArrayList<Photo>
    private var position: Int = 0
    private lateinit var recyclerView: RecyclerView
    private lateinit var detailsAdapter: DetailsAdapter

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onAttach(context: Context) {
        super.onAttach(context)

//        requireActivity().window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN or WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN)
//        requireActivity().window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_details, container, false)
        requireContext()
        initViews(view)
        (requireContext() as MainActivity).hideBottomNavigation()
        return view
    }

    private fun initViews(view: View) {

        items = Extensions.parseToList(arguments?.getString("photos")!!)
        position = arguments?.getInt("position")!!
        Logger.d("@@@", "Fragment -> ${items.size} -- $position")

        recyclerView = view.findViewById(R.id.rv_details)
        recyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        val snapHelper = PagerSnapHelper()
        snapHelper.attachToRecyclerView(recyclerView)
        refreshAdapter(items)
        recyclerView.scrollToPosition(position)

    }

    private fun refreshAdapter(items: ArrayList<Photo>){
        detailsAdapter = DetailsAdapter(requireContext(), this, items)
        recyclerView.adapter = detailsAdapter
    }

    override fun onResume() {
        super.onResume()
        (requireContext() as MainActivity).clearLightStatusBar()
    }

    override fun onDetach() {
        super.onDetach()
        requireActivity().window.clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
    }


}