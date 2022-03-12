package com.example.mypinterest.activity

import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.FrameLayout
import androidx.fragment.app.Fragment
import com.example.mypinterest.R
import com.example.mypinterest.fragments.MessageFragment
import com.example.mypinterest.fragments.PhotosFragment
import com.example.mypinterest.fragments.SearchFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    private lateinit var fm_fragments: FrameLayout
    private lateinit var bottom_nav: BottomNavigationView

    // fragments
    private lateinit var photosFragment: PhotosFragment
    private lateinit var searchFragment: SearchFragment
    private lateinit var messageFragment: MessageFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        window.decorView.systemUiVisibility= View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN

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
            if (supportFragmentManager.fragments.any { it is SearchFragment }){
                if (searchFragment.onBackPressed())
                    finish()
            }else finish()
        else
            super.onBackPressed()
    }

    fun showBottomNavigation(){
        bottom_nav.visibility = View.VISIBLE
    }

    fun hideBottomNavigation(){
        bottom_nav.visibility = View.GONE
    }

    fun setTransparentStatusBar() {
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.statusBarColor = Color.TRANSPARENT
        }
    }
}