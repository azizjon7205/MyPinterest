package com.example.mypinterest.activity

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.example.mypinterest.R
import com.google.android.material.bottomnavigation.BottomNavigationView

import androidx.navigation.fragment.NavHostFragment


class MainActivity : AppCompatActivity() {
    private var isLightStatusBar: Boolean = false

    private lateinit var bottom_nav: BottomNavigationView
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        window.decorView.systemUiVisibility= View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN

        initViews()
    }

    private fun initViews() {
        bottom_nav = findViewById(R.id.bottom_navigation)
        navController = findNavController(R.id.nav_host_fragment)
        appBarConfiguration = AppBarConfiguration(setOf(R.id.nav_home,
            R.id.nav_search,
            R.id.nav_message,
            R.id.nav_profile))
        bottom_nav.setupWithNavController(navController)

        val navHostFragment: NavHostFragment? = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment?
        navHostFragment!!.childFragmentManager.fragments[0]

    }

    override fun onBackPressed() {
        val currentFragment = navController.currentDestination?.id
        if (currentFragment != R.id.detailsFragment)
            showBottomNavigation()
        super.onBackPressed()
    }

    fun showBottomNavigation() {
        bottom_nav.visibility = View.VISIBLE
    }

    fun hideBottomNavigation() {
        bottom_nav.visibility = View.GONE
    }

    fun clearLightStatusBar() {
        if (!isLightStatusBar && Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//            var flags: Int = this.window.decorView.systemUiVisibility
//            flags = flags xor View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR  // use XOR here for remove LIGHT_STATUS_BAR from flags
//            this.window.decorView.systemUiVisibility = flags
//            this.window.statusBarColor = ContextCompat.getColor(this, R.color.black)
            window.decorView.systemUiVisibility =
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
            window.statusBarColor = Color.TRANSPARENT
            isLightStatusBar = true
        }

    }

    fun setLightStatusBar() {
        if (isLightStatusBar && Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

            window.decorView.systemUiVisibility =
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
            window.statusBarColor = Color.WHITE

            var flags: Int = this.window.decorView.systemUiVisibility
            flags = flags or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR // add LIGHT_STATUS_BAR to flag
            this.window.decorView.systemUiVisibility = flags
            this.window.statusBarColor = ContextCompat.getColor(this, R.color.white)
            isLightStatusBar = false

        }
    }

    @SuppressLint("ServiceCast")
    fun closeKeyBoard() {
        val view = this.currentFocus
        if (view != null) {
            val imm = this.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }

}