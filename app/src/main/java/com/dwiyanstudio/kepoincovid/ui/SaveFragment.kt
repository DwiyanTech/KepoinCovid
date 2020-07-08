package com.dwiyanstudio.kepoincovid.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.dwiyanstudio.kepoincovid.R
import com.dwiyanstudio.kepoincovid.adapter.SaveAdapter
import com.dwiyanstudio.kepoincovid.config.NewsDatabase
import com.dwiyanstudio.kepoincovid.data.NewsData
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.info_section.*

class SaveFragment : Fragment() {

    private var newsDatabase : NewsDatabase? = null
    private val compositeDispose  = CompositeDisposable()
    private val newsData :ArrayList<NewsData> =  ArrayList()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.info_section,container,false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        newsDatabase = context?.applicationContext?.let { NewsDatabase.getNewsBuild(it) }
        progress_news.visibility = View.VISIBLE
        getDataSave()

    }

    private fun getDataSave(){
        compositeDispose.add(Observable.fromCallable { newsDatabase!!.newsDao().getNews() }.subscribeOn(Schedulers.computation()).observeOn(AndroidSchedulers.mainThread()).subscribe {
            newsData.clear()
            newsData.addAll(it)
            progress_news.visibility = View.INVISIBLE
            news_recycler.layoutManager = GridLayoutManager(context,2)
            val adapterSave = SaveAdapter(newsData)
            news_recycler.adapter = adapterSave
            touchHelper()
        })


    }

    fun touchHelper(){
        val helper = object : ItemTouchHelper.SimpleCallback(0,
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT){
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                if(newsData.size < 0) {
                    compositeDispose.add(Observable.fromCallable { newsDatabase!!.newsDao().deleteAllNews() }
                        .subscribeOn(Schedulers.computation()).observeOn(AndroidSchedulers.mainThread()).subscribe { error -> println(error)
                        }
                    )
                } else {
                    compositeDispose.add(Observable.fromCallable { newsDatabase!!.newsDao().deleteNews(newsData[position]) }
                        .subscribeOn(Schedulers.computation()).observeOn(AndroidSchedulers.mainThread()).subscribe { error -> println(error)
                        }
                    )
                }
            }

        }
        val itemTouchHelper = ItemTouchHelper(helper)
        itemTouchHelper.attachToRecyclerView(news_recycler)
    }
}