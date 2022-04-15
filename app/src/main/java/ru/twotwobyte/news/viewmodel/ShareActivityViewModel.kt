package ru.twotwobyte.news.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import ru.twotwobyte.news.api.ApiFactory
import ru.alexleru.movieshustov.database.AppDatabase

class ShareActivityViewModel(application: Application) : AndroidViewModel(application) {

    private val db = AppDatabase.getInstance(application)
    private val compositeDisposable = CompositeDisposable()
    val article = db.movieDao().queryArticles()

    init {
        loadTopHeadlines()
    }

    fun loadTopHeadlines() {
        val disposable = ApiFactory.apiService.getTopHeadlines()
            .subscribeOn(Schedulers.io())
            .subscribe({
                db.movieDao().deleteArticles()
                db.movieDao().insertArticles(it.articles)
            }, {
                Log.e("Error", it.message.toString())
            })
        compositeDisposable.add(disposable)
    }

    fun loadSearchHeadlines(text: String) {
        val disposable = ApiFactory.apiService.getSearchHeadlines(query = text)
            .subscribeOn(Schedulers.io())
            .subscribe({
                db.movieDao().deleteArticles()
                db.movieDao().insertArticles(it.articles)
            }, {
                Log.e("Error", it.message.toString())
            })
        compositeDisposable.add(disposable)
    }


    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}