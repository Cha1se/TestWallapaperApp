package com.example.testwallapaperapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.FrameLayout
import androidx.constraintlayout.widget.ConstraintLayout

class WebViewActivity : AppCompatActivity() {

    lateinit var webView: WebView
    lateinit var mainLay: ConstraintLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_view)

        mainLay = findViewById(R.id.mainLayWebView)

        webView = WebView(this)

        createWebView()
    }

    fun createWebView () {

        var webView = WebView(this)

        webView.webViewClient = WebViewClient()
        webView.loadUrl("https://html5test.com/")

        webView.settings.setSupportZoom(true)

        mainLay.addView(webView)

    }

    override fun onBackPressed() {
        if (webView.canGoBack())
            webView.goBack()
    }

}