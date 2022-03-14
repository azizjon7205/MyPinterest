package com.example.mypinterest.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.ViewPager
import com.example.mypinterest.R
import com.example.mypinterest.adapter.ViewPagerAdapter
import com.google.android.material.tabs.TabLayout

class MessageFragment private constructor(): Fragment() {

    companion object{
        private var fragment: MessageFragment? = null

        fun newInstance(): MessageFragment?{
            if (fragment == null){
                fragment = MessageFragment()
            }
            return fragment
        }
    }


    private lateinit var pagerAdapter: ViewPagerAdapter
    private lateinit var viewPager: ViewPager
    private lateinit var tabLayout: TabLayout

    // fragments
    private lateinit var updatesFragment: UpdatesFragment
    private lateinit var contactFragnent: ContactFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initFragments()
        setupAdapter()
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_message, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initFragments()
        initViews(view)
    }

    private fun initFragments(){
        updatesFragment = UpdatesFragment.newInstance()!!
        contactFragnent = ContactFragment.newInstance()!!
    }

    private fun initViews(view: View) {
        tabLayout = view.findViewById(R.id.tabLayout)
        viewPager = view.findViewById(R.id.viewPager)
        viewPager.adapter = ViewPagerAdapter(parentFragmentManager)

        refreshAdapter()
//        changeIconVisible(ivParams, viewPager.currentItem)

        viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageSelected(position: Int) {
//                changeIconVisible(ivParams, position)
            }

            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
            }

            override fun onPageScrollStateChanged(state: Int) {}
        })
    }

    private fun setupAdapter() {
        pagerAdapter = ViewPagerAdapter(parentFragmentManager)
        pagerAdapter.add(updatesFragment, "Updates")
        pagerAdapter.add(contactFragnent, "Messages")
    }

    private fun refreshAdapter(){
        viewPager.adapter = pagerAdapter
        tabLayout.setupWithViewPager(viewPager)
    }

    override fun onResume() {
        super.onResume()
        initFragments()
        setupAdapter()
        refreshAdapter()
    }

}