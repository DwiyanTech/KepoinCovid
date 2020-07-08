package com.dwiyanstudio.kepoincovid.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.dwiyanstudio.kepoincovid.config.ConfigApi
import com.dwiyanstudio.kepoincovid.R
import com.loopj.android.http.AsyncHttpClient
import com.loopj.android.http.AsyncHttpResponseHandler
import cz.msebera.android.httpclient.Header
import kotlinx.android.synthetic.main.splash_screen.*

class SplashActivity : AppCompatActivity() {
    private val url =  "https://api.covid19api.com/summary"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash_screen)
        onLoad()
    }

    private fun onLoad(){
        val client = AsyncHttpClient()
        client.get(ConfigApi.URL_COUNT_COVID,object :AsyncHttpResponseHandler(){
            override fun onSuccess(
                statusCode: Int,
                headers: Array<out Header>?,
                responseBody: ByteArray?
            ) {
            loading_internet.visibility = View.INVISIBLE // this For Progress Bar
            val intent = Intent(this@SplashActivity,
                MainActivity::class.java)
                startActivity(intent)
                finish()
            }

            override fun onFailure(
                statusCode: Int,
                headers: Array<out Header>?,
                responseBody: ByteArray?,
                error: Throwable?
            ) {
                Toast.makeText(this@SplashActivity,"Koneksi Ke Internet Gagal",Toast.LENGTH_LONG).show()            }

        })
    }

}