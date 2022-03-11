package com.example.mypinterest.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.mypinterest.R

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


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_message, container, false)
    }

}