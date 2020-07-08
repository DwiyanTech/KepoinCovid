package com.dwiyanstudio.kepoincovid.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.dwiyanstudio.kepoincovid.config.ConfigApi
import com.dwiyanstudio.kepoincovid.adapter.NewsAdapter
import com.dwiyanstudio.kepoincovid.data.NewsList
import com.dwiyanstudio.kepoincovid.R
import com.loopj.android.http.AsyncHttpClient
import com.loopj.android.http.AsyncHttpResponseHandler
import cz.msebera.android.httpclient.Header
import kotlinx.android.synthetic.main.info_section.*
import org.json.JSONArray
import org.json.JSONObject

class NewsFragment : Fragment() {

    private val listNews = ArrayList<NewsList>()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.info_section,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        progress_news.visibility = View.VISIBLE
        news_recycler.visibility = View.INVISIBLE
        getData()


    }

    private fun getData(){
        val client = AsyncHttpClient()
        client.get(ConfigApi.URL_NEWS, object : AsyncHttpResponseHandler(){
            override fun onSuccess(
                statusCode: Int,
                headers: Array<out Header>?,
                responseBody: ByteArray
            ) {

                progress_news.visibility = View.INVISIBLE
                news_recycler.visibility = View.VISIBLE
                val response = String(responseBody)
                val jsonResponse = JSONObject(response)
                val jsonData = jsonResponse.getString("articles")
                val jsonArray = JSONArray(jsonData)
                for(i in 0 until jsonArray.length()){
                    val newsObject = jsonArray.getJSONObject(i)
                    val newsTitle = newsObject.getString("title")
                    val imageUrl = newsObject.getString("urlToImage")
                    val newsUrl = newsObject.getString("url")
                    val newsData = NewsList(
                        newsTitle,
                        imageUrl,
                        newsUrl
                    )
                    listNews.add(newsData)
                }

                news_recycler.layoutManager = GridLayoutManager(context,2)
                val adapterNews =
                    NewsAdapter(listNews)
                news_recycler.adapter = adapterNews

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