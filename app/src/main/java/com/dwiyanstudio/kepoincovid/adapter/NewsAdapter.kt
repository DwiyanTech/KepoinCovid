package com.dwiyanstudio.kepoincovid.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.dwiyanstudio.kepoincovid.data.NewsList
import com.dwiyanstudio.kepoincovid.R
import com.dwiyanstudio.kepoincovid.config.NewsDatabase
import com.dwiyanstudio.kepoincovid.data.NewsData
import com.dwiyanstudio.kepoincovid.ui.WebNewsActivity
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.news_view.view.*
import kotlin.collections.ArrayList

class NewsAdapter(private val newsList : ArrayList<NewsList>) : RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {

    private var newsDatabase : NewsDatabase? = null
    private val compositeDispose  = CompositeDisposable()

   inner class NewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(getNews : NewsList){

            with(itemView){
                newsDatabase = NewsDatabase.getNewsBuild(context)

                Glide.with(itemView.context)
                    .load(getNews.image)
                    .apply(RequestOptions().override(180,120))
                    .into(news_item_photo)
                text_card.text = getNews.title
                val newsInsert = NewsData(getNews.title.toString(),getNews.image.toString(),getNews.link.toString())

                save_button.setOnClickListener {
                    insertDataNews(newsInsert)
                    Toast.makeText(context,"Berita berhasil Disimpan",Toast.LENGTH_LONG).show()
                }
                itemView.setOnClickListener{
                    val webIntent = Intent(context ,
                        WebNewsActivity::class.java)
                    webIntent.putExtra(WebNewsActivity.EXTRA_URL,getNews.link)
                    context.startActivity(webIntent)
                }
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.news_view,parent,false)
        return NewsViewHolder(view)
    }

    override fun getItemCount(): Int = newsList.size


    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.bind(newsList[position])
    }

    /* Start Room Query */

    fun insertDataNews(insertNews : NewsData){
        compositeDispose.add(Observable.fromCallable { newsDatabase!!.newsDao().insertNews(insertNews) }
            .subscribeOn(Schedulers.computation()).observeOn(AndroidSchedulers.mainThread()).subscribe()
        )
    }

}