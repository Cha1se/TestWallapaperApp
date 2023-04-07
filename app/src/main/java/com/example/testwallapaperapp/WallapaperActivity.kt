package com.example.testwallapaperapp

import android.app.WallpaperManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.children
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import kotlin.concurrent.thread

class WallapaperActivity : AppCompatActivity() {

    lateinit var viewPager: ViewPager2
    lateinit var setWall: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_wallapaper)

        viewPager = findViewById(R.id.viewPager)
        viewPager.adapter = ViewPagerAdapter(supportFragmentManager, lifecycle)

        setWall = findViewById(R.id.setImgButton)

        setWall.setOnClickListener {
            setWallpaper()
        }

    }

    fun setWallpaper() {
        var image = R.drawable.wall1

        if (viewPager.currentItem == 0) {
            image = R.drawable.wall1
        } else if (viewPager.currentItem == 1) {
            image = R.drawable.wall2
        } else {
            image = R.drawable.wall3
        }

        val bitmap: Bitmap =
            BitmapFactory.decodeResource(resources, image)

        val wallpaperManager = WallpaperManager.getInstance(baseContext)
        wallpaperManager.setBitmap(bitmap)
        Toast.makeText(this, "Wallpaper set!", Toast.LENGTH_SHORT).show()
    }

    override fun onBackPressed() {}

}