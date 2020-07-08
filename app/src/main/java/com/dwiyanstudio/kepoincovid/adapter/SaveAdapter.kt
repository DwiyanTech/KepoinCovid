package com.dwiyanstudio.kepoincovid.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.dwiyanstudio.kepoincovid.R
import com.dwiyanstudio.kepoincovid.config.NewsDatabase
import com.dwiyanstudio.kepoincovid.data.NewsData
import com.dwiyanstudio.kepoincovid.ui.WebNewsActivity
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.save_view.view.*

class SaveAdapter(private val saveNews : ArrayList<NewsData>): RecyclerView.Adapter<SaveAdapter.SaveViewHolder>() {

    private var newsDatabase : NewsDatabase? = null
    private val compositeDispose  = CompositeDisposable()
    class SaveViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(getNews : NewsData){
            with(itemView){

                Glide.with(itemView.context)
                    .load(getNews.imageLink)
                    .apply { RequestOptions().override(180,120) }
                    .into(news_save_photo)
                text_save.text = getNews.title
                itemView.setOnClickListener{
                    val webIntent = Intent(context ,
                        WebNewsActivity::class.java)
                    webIntent.putExtra(WebNewsActivity.EXTRA_URL,getNews.link)
                    context.startActivity(webIntent)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SaveViewHolder {
        val viewSave = LayoutInflater.from(parent.context).inflate(R.layout.save_view,parent,false)
        return SaveViewHolder(viewSave)
    }

    override fun getItemCount(): Int {
       return saveNews.size
    }

    override fun onBindViewHolder(holder: SaveViewHolder, position: Int) {
        holder.bind(saveNews[position])
    }


}