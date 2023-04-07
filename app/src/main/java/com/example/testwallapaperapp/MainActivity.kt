package com.example.testwallapaperapp

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.io.BufferedInputStream
import java.io.File
import java.io.InputStream
import java.net.URL
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths

class MainActivity : AppCompatActivity() {

    lateinit var mainLayout: ConstraintLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainLayout = findViewById(R.id.mainLay)

        val audioUrl = "https://testtasks.nutgeek.fun/a/"
        downloadAudio(audioUrl)
    }

    fun downloadAudio(url: String) {

        var isOne: Boolean = false

        Observable.fromCallable {

            var filename = "current.mp3"

            if (File(filesDir.absolutePath + "/current.mp3").exists()) {
                File(filesDir.absolutePath + "/current.mp3").delete()
            }

            downloadFile(URL(url), filesDir.absolutePath + "/current.mp3")

            val oneIns: InputStream = resources.openRawResource(
                resources.getIdentifier(
                    "one",
                    "raw", packageName
                )
            )

            val twoIns: InputStream = resources.openRawResource(
                resources.getIdentifier(
                    "two",
                    "raw", packageName
                )
            )

            oneIns.toFile(filesDir.absolutePath + "/one.mp3")
            twoIns.toFile(filesDir.absolutePath + "/two.mp3")

            var currentFile = File(filesDir.absolutePath + "/" + filename)
            Files.size(currentFile.toPath())

            if (Files.size(currentFile.toPath()) == Files.size(File(filesDir.absolutePath + "/one.mp3").toPath())) {
                Log.e("atg", "ONE")
                isOne = true
            } else if (Files.size(currentFile.toPath()) == Files.size(File(filesDir.absolutePath + "/two.mp3").toPath())) {
                Log.e("atg", "TWO")
                isOne = false
            } else {
                Log.e("atg", "Not working")
            }

            oneIns.close()
            twoIns.close()

        }.doOnComplete {

            if (isOne) {

                startActivity(Intent(this, WallapaperActivity::class.java))
                overridePendingTransition(
                    androidx.appcompat.R.anim.abc_fade_in,
                    androidx.appcompat.R.anim.abc_fade_out
                )

            } else {

                startActivity(Intent(this, WebViewActivity::class.java))
                overridePendingTransition(
                    androidx.appcompat.R.anim.abc_fade_in,
                    androidx.appcompat.R.anim.abc_fade_out
                )

            }

        }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe()
    }

    fun InputStream.toFile(path: String) {
        File(path).outputStream().use { this.copyTo(it) }
    }

    fun downloadFile(url: URL, fileName: String) {
        url.openStream().use { Files.copy(it, Paths.get(fileName)) }
    }

}