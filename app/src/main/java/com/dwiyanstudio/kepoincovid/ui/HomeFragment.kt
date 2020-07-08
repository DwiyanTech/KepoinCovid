package com.dwiyanstudio.kepoincovid.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.dwiyanstudio.kepoincovid.config.ConfigApi
import com.dwiyanstudio.kepoincovid.R
import com.loopj.android.http.AsyncHttpClient
import com.loopj.android.http.AsyncHttpResponseHandler
import cz.msebera.android.httpclient.Header
import kotlinx.android.synthetic.main.home_section.*
import org.json.JSONObject
import java.lang.Exception

class HomeFragment : Fragment() {
    public override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.home_section,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Toast.makeText(context, "Data Sedang Dimuat...", Toast.LENGTH_LONG).show()
        getDataCovid()

    }

    private fun getDataCovid(){
        val client = AsyncHttpClient()
        client.get(ConfigApi.URL_COUNT_COVID, object : AsyncHttpResponseHandler(){
            override fun onSuccess(
                statusCode: Int,
                headers: Array<out Header>?,
                responseBody: ByteArray
            ) {
                val resultJson = String(responseBody)
                try {
                val resGlobal = JSONObject(resultJson)
                    val covid  = resGlobal.getString("Global")
                    val res = JSONObject(covid)
                    angka_positif.text = res.getString("TotalConfirmed")
                    angka_meninggal.text = res.getString("TotalDeaths")
                    angka_sembuh.text = res.getString("TotalRecovered")
                }catch (e:Exception){

                }
            }

            override fun onFailure(
                statusCode: Int,
                headers: Array<out Header>?,
                responseBody: ByteArray?,
                error: Throwable?
            ) {

            }

        })
    }
}