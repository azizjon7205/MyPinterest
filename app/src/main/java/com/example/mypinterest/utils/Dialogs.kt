package com.example.mypinterest.utils

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.app.DownloadManager
import android.content.Context
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.util.Log
import android.view.Gravity
import android.widget.LinearLayout
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat.checkSelfPermission
import com.example.mypinterest.R
import com.example.mypinterest.database.MyPhoto
import com.example.mypinterest.managers.RoomManager
import com.example.mypinterest.model.Photo
import com.example.mypinterest.utils.Utils.Companion.haveStoragePermission
import com.google.android.material.bottomsheet.BottomSheetDialog
import java.io.File


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
                val myPhoto = MyPhoto(photo.id!!,
                    photo.color,
                    photo.description,
                    photo.urls!!.small,
                    photo.likes)
                RoomManager.instance!!.photoDao().savePhoto(myPhoto)
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