package com.app.androidmvvm.views.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.androidmvvm.adapters.HomeAdapter
import com.app.androidmvvm.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var homeAdapter : HomeAdapter
    private lateinit var homeActivityViewModelFactory : HomeActivityViewModelFactory
    private lateinit var homeActivityViewModel : HomeActivityViewModel

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(this.binding.root)

        initialRecyclerView()

        this.homeActivityViewModelFactory = HomeActivityViewModelFactory(this)
        this.homeActivityViewModel = ViewModelProvider(this, homeActivityViewModelFactory).get(HomeActivityViewModel::class.java)

        this.homeActivityViewModel.getPopularMovies().observe(this, Observer {
            this.homeAdapter.popularMovies = it
            this.homeAdapter.notifyDataSetChanged()
        })
    }

    private fun initialRecyclerView() {
        this.homeAdapter = HomeAdapter()
        this.binding.PopularMovieList.layoutManager = LinearLayoutManager(this)
        this.binding.PopularMovieList.adapter = homeAdapter
    }
}