package com.app.androidmvvm.views.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import com.app.androidmvvm.R

class MainActivity : AppCompatActivity() {

    private val homeActivityModelView : HomeActivityModelView = HomeActivityModelView()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        homeActivityModelView.getPopularMovies().observe(this, Observer {
            it.forEach { movie ->
                Log.d("Debug", movie.title)
            }
        })
    }
}