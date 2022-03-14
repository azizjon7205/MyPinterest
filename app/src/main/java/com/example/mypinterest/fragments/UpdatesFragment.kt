package com.example.mypinterest.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mypinterest.R
import com.example.mypinterest.adapter.CollectionsAdapter
import com.example.mypinterest.model.Collection
import com.example.mypinterest.network.RetrofitHttp
import com.example.mypinterest.utils.Logger
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class UpdatesFragment : Fragment() {

    companion object {
        private var fragment: UpdatesFragment? = null

        fun newInstance(): UpdatesFragment? {
            if (fragment == null) {
                fragment = UpdatesFragment()
            }
            return fragment
        }
    }

    private lateinit var collectionsAdapter: CollectionsAdapter
    private lateinit var recyclerUpdates: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        collectionsAdapter = CollectionsAdapter(requireContext())
        getCollections()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        val  view = inflater.inflate(R.layout.fragment_updates, container, false)
        initViews(view)
        return view
    }

    private fun initViews(view: View) {
        recyclerUpdates = view.findViewById(R.id.rv_updates)
        recyclerUpdates.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL, false)
        recyclerUpdates.adapter = collectionsAdapter
    }

    private fun getCollections() {
        RetrofitHttp.photosService.getCollections()
            .enqueue(object : Callback<ArrayList<Collection>> {
                @SuppressLint("NotifyDataSetChanged")
                override fun onResponse(
                    call: Call<ArrayList<Collection>>,
                    response: Response<ArrayList<Collection>>,
                ) {
                    if (response.isSuccessful) {
//                    refreshAdapterTopics(response.body()!!)
                        collectionsAdapter.items.addAll(response.body()!!)
                        collectionsAdapter.notifyDataSetChanged()
                    }
                    Logger.d("@@@", "Collection -> ${response.body()}")
                }

                override fun onFailure(call: Call<ArrayList<Collection>>, t: Throwable) {
                    Logger.d("@@@", "Collection -> ${t.localizedMessage}")
                }

            })
    }
}