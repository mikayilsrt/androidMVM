package com.app.androidmvvm.views.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.androidmvvm.R
import com.app.androidmvvm.adapters.HomeAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var homeAdapter : HomeAdapter
    private lateinit var homeActivityViewModelFactory : HomeActivityViewModelFactory
    private lateinit var homeActivityModelView : HomeActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initialRecyclerView()

        this.homeActivityViewModelFactory = HomeActivityViewModelFactory(this)
        this.homeActivityModelView = ViewModelProvider(this, homeActivityViewModelFactory).get(HomeActivityViewModel::class.java)

        this.homeActivityModelView.getPopularMovies().observe(this, Observer {
            this.homeAdapter.popularMovies = it
            this.homeAdapter.notifyDataSetChanged()
        })
    }

    private fun initialRecyclerView() {
        this.homeAdapter = HomeAdapter()
        _popularMovieList.layoutManager = LinearLayoutManager(this)
        _popularMovieList.adapter = homeAdapter
    }
}