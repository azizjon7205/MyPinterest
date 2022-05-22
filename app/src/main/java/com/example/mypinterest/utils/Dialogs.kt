package com.example.mypinterest.utils

import android.annotation.SuppressLint
import android.content.Context
import android.view.Gravity
import android.widget.LinearLayout
import android.widget.Toast
import com.example.mypinterest.R
import com.example.mypinterest.managers.RoomManager
import com.example.mypinterest.model.Photo
import com.example.mypinterest.model.Pin
import com.google.android.material.bottomsheet.BottomSheetDialog


class Dialogs {

    companion object {


        @SuppressLint("CheckResult")
        fun showBottomSheetDialog(context: Context, photo: Photo) {
            val bottomSheetDialog = BottomSheetDialog(context)
            bottomSheetDialog.setContentView(R.layout.bottom_sheet_dialog)

            val copy = bottomSheetDialog.findViewById<LinearLayout>(R.id.ll_copy)
            val share = bottomSheetDialog.findViewById<LinearLayout>(R.id.ll_share)
            val upload = bottomSheetDialog.findViewById<LinearLayout>(R.id.ll_upload_profile)
            val download = bottomSheetDialog.findViewById<LinearLayout>(R.id.ll_download)
            val delete = bottomSheetDialog.findViewById<LinearLayout>(R.id.ll_delete)

            upload!!.setOnClickListener {
                RoomManager.instance!!.pinDao().savePhoto(Pin(0, photo))

                val toast = Toast.makeText(context, "\tImage Saved to Profile\t", Toast.LENGTH_LONG)
                toast.setGravity(Gravity.TOP, 0, 0)
                toast.show()
                bottomSheetDialog.dismiss()
            }

            download!!.setOnClickListener {
                Utils.saveImageToGallery(context, photo.urls!!.regular!!)
                bottomSheetDialog.dismiss()
            }

            bottomSheetDialog.show()
        }


    }

}