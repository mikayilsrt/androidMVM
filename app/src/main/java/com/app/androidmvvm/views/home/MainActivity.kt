package com.app.androidmvvm.views.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.androidmvvm.R
import com.app.androidmvvm.adapters.HomeAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var homeActivityViewModelFactory : HomeActivityViewModelFactory
    private lateinit var homeActivityModelView : HomeActivityModelView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        homeActivityViewModelFactory = HomeActivityViewModelFactory(this)
        homeActivityModelView = ViewModelProvider(this, homeActivityViewModelFactory).get(HomeActivityModelView::class.java)

        val homeAdapter : HomeAdapter = HomeAdapter()
        _popularMovieList.layoutManager = LinearLayoutManager(this)
        _popularMovieList.adapter = homeAdapter

        homeActivityModelView.getPopularMovies().observe(this, Observer {
            homeAdapter.popularMovies = it
            homeAdapter.notifyDataSetChanged()
        })
    }
}