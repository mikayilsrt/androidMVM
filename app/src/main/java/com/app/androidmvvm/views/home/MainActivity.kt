package com.app.androidmvvm.views.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.app.androidmvvm.R

class MainActivity : AppCompatActivity() {

    private lateinit var homeActivityViewModelFactory : HomeActivityViewModelFactory
    private lateinit var homeActivityModelView : HomeActivityModelView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        homeActivityViewModelFactory = HomeActivityViewModelFactory(this)
        homeActivityModelView = ViewModelProvider(this, homeActivityViewModelFactory).get(HomeActivityModelView::class.java)

        homeActivityModelView.getPopularMovies().observe(this, Observer {
            it.forEach { movie ->
                Log.d("Debug", movie.title)
            }
        })
    }
}