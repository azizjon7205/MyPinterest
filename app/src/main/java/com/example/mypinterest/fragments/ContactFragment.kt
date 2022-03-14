package com.example.mypinterest.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.mypinterest.R


class ContactFragment : Fragment() {

    companion object {
        private var fragment: ContactFragment? = null

        fun newInstance(): ContactFragment? {
            if (fragment == null) {
                fragment = ContactFragment()
            }
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_contact, container, false)
    }

}