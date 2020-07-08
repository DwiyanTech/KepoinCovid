package com.dwiyanstudio.kepoincovid.config

class ConfigApi {
    companion object{
         private const val api_news = "YOUR_KEY"
         const val URL_NEWS = "https://newsapi.org/v2/everything?q=corona&domains=tribunnews.com,detik.com,kompas.com&apiKey=$api_news"
         const val URL_COUNT_COVID= "https://api.covid19api.com/summary"
    }

}