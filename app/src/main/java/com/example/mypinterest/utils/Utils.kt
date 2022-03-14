package com.example.mypinterest.utils

import android.Manifest
import android.app.Activity
import android.app.DownloadManager
import android.content.Context
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.util.Log
import android.view.Gravity
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import java.io.File

class Utils {
    companion object{
        fun saveImageToGallery(context: Context, url: String){
            if (haveStoragePermission(context)){
                imageDownloader(context, url)
                val toast = Toast.makeText(context, "\t\tImage Saved to Gallery\t\t", Toast.LENGTH_LONG)
                toast.setGravity(Gravity.TOP, 0, 0)
                toast.show()
            }
            else
                Toast.makeText(context, "You haven`t allowed to access storage. Try again", Toast.LENGTH_SHORT).show()

        }

        val DIR_NAME = "MyPinterest"
        private fun imageDownloader(context: Context, url: String) {
            val filename = "filename.jpg"
            val downloadUrlOfImage = url
            val direct = File(Environment
                .getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)
                .absolutePath + "/" + DIR_NAME + "/")

            if (!direct.exists()) {
                direct.mkdir()
                Log.d(DIR_NAME, "dir created for first time")
            }

            val downloadUri = Uri.parse(downloadUrlOfImage)
            val request = DownloadManager.Request(downloadUri)
            request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI or DownloadManager.Request.NETWORK_MOBILE)
                .setAllowedOverRoaming(false)
                .setTitle(filename)
                .setMimeType("image/jpeg")
                .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
                .setDestinationInExternalPublicDir(Environment.DIRECTORY_PICTURES,
                    File.separator + DIR_NAME + File.separator.toString() + filename)

            (context.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager).enqueue(request)

        }

        fun haveStoragePermission(context: Context): Boolean {
            return if (Build.VERSION.SDK_INT >= 23) {
                if (ContextCompat.checkSelfPermission(context,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE) === PackageManager.PERMISSION_GRANTED) {
                    Log.e("Permission error", "You have permission")
                    true
                } else {
                    Log.e("Permission error", "You have asked for permission")
                    ActivityCompat.requestPermissions(context as Activity,
                        arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
                        1)
                    false
                }
            } else { //you dont need to worry about these stuff below api level 23
                Log.e("Permission error", "You already have the permission")
                true
            }
        }
    }
}