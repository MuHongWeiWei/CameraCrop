package com.example.aaaa

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.troy.cameralib.EasyCamera
import java.io.File


class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
            val destination = Uri.fromFile(File(cacheDir, "456.jpg"))
            EasyCamera.create(destination)
                .withViewRatio(0.5f)
                .withMarginCameraEdge(50, 50)
                .start(this@MainActivity)
        } else {
            ActivityCompat.requestPermissions(
                this, arrayOf(Manifest.permission.CAMERA),
                5
            )
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
            val imageView = findViewById<ImageView>(R.id.imageView)
            val resultUri = EasyCamera.getOutput(data!!)
            val width = EasyCamera.getImageWidth(data)
            val height = EasyCamera.getImageHeight(data)
            imageView.setImageURI(resultUri)
        }

}