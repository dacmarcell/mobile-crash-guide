package com.shrey.newsbee.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ProgressBar
import com.shrey.newsbee.R

class WebActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web)

        // Get URL from intent
        val url = intent.getStringExtra("URL")
        if (url != null) {
            // Find WebView in layout
            val webDetailedView: WebView = findViewById(R.id.webDetailedView)
            // Enable JavaScript in WebView settings
            webDetailedView.settings.javaScriptEnabled = true
            // Set user agent string for the WebView
            webDetailedView.settings.userAgentString =
                "Mozilla/5.0 (Linux; Android 11; Pixel 5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.131 Mobile Safari/537.36"
            // Set WebViewClient to handle page loading events
            webDetailedView.webViewClient = object : WebViewClient() {
                override fun onPageFinished(view: WebView?, url: String?) {
                    super.onPageFinished(view, url)
                    // Hide progress bar and show WebView when page finishes loading
                    val progressBar: ProgressBar = findViewById(R.id.progressBar)
                    progressBar.visibility = View.GONE
                    webDetailedView.visibility = View.VISIBLE
                }
            }
            // Load the URL in the WebView
            webDetailedView.loadUrl(url)
        }
    }
}
