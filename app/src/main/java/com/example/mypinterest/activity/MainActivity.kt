package com.example.mypinterest.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.FrameLayout
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.example.mypinterest.R
import com.example.mypinterest.adapter.ViewPagerAdapter
import com.example.mypinterest.fragments.MessageFragment
import com.example.mypinterest.fragments.PhotosFragment
import com.example.mypinterest.fragments.SearchFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    private lateinit var fm_fragments: FrameLayout
    private lateinit var viewPagerAdapter: ViewPagerAdapter
    private lateinit var bottom_nav: BottomNavigationView

    // fragments
    private lateinit var photosFragment: PhotosFragment
    private lateinit var searchFragment: SearchFragment
    private lateinit var messageFragment: MessageFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initFragments()
        initViews()
    }

    private fun initFragments(){
        photosFragment = PhotosFragment.newInstance()!!
        searchFragment = SearchFragment.newInstance()!!
        messageFragment = MessageFragment.newInstance()!!
    }

    private fun initViews(){
        fm_fragments = findViewById(R.id.fm_fragments)

        replaceFragment(photosFragment)
        bottom_nav = findViewById(R.id.bottom_navigation)
        bottom_nav.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.nav_home -> replaceFragment(photosFragment)
                R.id.nav_search -> replaceFragment(searchFragment)
                R.id.nav_message -> replaceFragment(messageFragment)
            }
            true
        }
    }


     fun replaceFragment(fragment: Fragment) {
        val backStateName = fragment.javaClass.name
        val manager = supportFragmentManager
        val fragmentPopped = manager.popBackStackImmediate(backStateName, 0)
        if (!fragmentPopped) {
            val ft = manager.beginTransaction()
            ft.replace(R.id.fm_fragments, fragment)
            ft.addToBackStack(backStateName)
            ft.commit()
        }
    }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount == 1)
            finish()
        else
            super.onBackPressed()
    }

    fun showBottomNavigation(){
        bottom_nav.visibility = View.VISIBLE
    }

    fun hideBottomNavigation(){
        bottom_nav.visibility = View.GONE
    }



}