package ru.twotwobyte.news

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import ru.twotwobyte.news.viewmodel.ConnectivityStatus
import ru.twotwobyte.news.viewmodel.NetworkStatus
import ru.twotwobyte.news.viewmodel.ShareActivityViewModel


class MainActivity : AppCompatActivity(), SearchView.OnQueryTextListener {

    private lateinit var recyclerView: RecyclerView
    private val sharedViewModel: ShareActivityViewModel by lazy {
        ViewModelProvider(this)[ShareActivityViewModel::class.java]
    }
    private val adapter: NewsAdapter by lazy { NewsAdapter() }
    private lateinit var searchView: SearchView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.m_toolbar))

        recyclerView = findViewById(R.id.recycler_item)
        recyclerView.adapter = adapter
        sharedViewModel.article.observe(this) {
            adapter.listOfNews = it
            findViewById<TextView>(R.id.findTextView).visibility = when (adapter.listOfNews.size) {
                0 -> View.VISIBLE
                else -> View.GONE
            }
        }
        adapter.onClickListenerNewsAdapter = NewsAdapter.OnClickListenerNews {

            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(it)))
        }

        ConnectivityStatus(this@MainActivity).observe(this) {
            findViewById<TextView>(R.id.statusInetTextView).visibility = when (it) {
                NetworkStatus.Available -> View.GONE
                NetworkStatus.Unavailable -> View.VISIBLE
            }
            Log.i("status===", it.toString())
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        val search = menu?.findItem(R.id.search_menu)
        searchView = search?.actionView as SearchView
        searchView.isSubmitButtonEnabled
        searchView.setOnQueryTextListener(this)
        return true
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        checkTextAndLoadData(query)
        return false
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        checkTextAndLoadData(newText)
        return true
    }

    private fun checkTextAndLoadData(text: String?) {

        if (text.isNullOrBlank())
            sharedViewModel.loadTopHeadlines()
        else sharedViewModel.loadSearchHeadlines(text)
        sharedViewModel.article.observe(this) { adapter.listOfNews = it }
    }
}