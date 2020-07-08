package com.dwiyanstudio.kepoincovid.ui

import android.graphics.Bitmap
import android.os.Bundle
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.dwiyanstudio.kepoincovid.R
import kotlinx.android.synthetic.main.web_view.*

class WebNewsActivity : AppCompatActivity(), View.OnClickListener {

    companion object {
        const val EXTRA_URL = "URL_INTENT"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.web_view)

        val urlNews = intent.getStringExtra(EXTRA_URL)
        exit_btn.setOnClickListener(this)
        web_view.settings.javaScriptEnabled = true
        web_view.webViewClient = object : WebViewClient(){
            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                Toast.makeText(applicationContext, "Memuat...", Toast.LENGTH_LONG).show()
            }
        }
        if (urlNews != null) {
            web_view.loadUrl(urlNews)
        }
    }

    override fun onClick(p0: View?) {
       finish()
    }

}
