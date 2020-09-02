package com.app.androidmvvm.views.movie

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.app.androidmvvm.R

class MovieActivity : AppCompatActivity() {

    companion object {
        private const val EXTRA_MOVIE_ID = "EXTRA_MOVIE_ID"

        fun start(context: Context, movieId: Int) {
            context.startActivity(
                Intent(context, MovieActivity::class.java).putExtra(EXTRA_MOVIE_ID, movieId)
            )
        }
    }

    private val movieIdExtra by lazy {
        intent.getIntExtra(EXTRA_MOVIE_ID, -1)
    }

    private lateinit var movieActivityViewModel : MovieActivityViewModel
    private lateinit var movieActivityViewModelFactory: MovieActivityViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie)

        this.movieActivityViewModelFactory = MovieActivityViewModelFactory(this, movieIdExtra)
        this.movieActivityViewModel = ViewModelProvider(this, this.movieActivityViewModelFactory).get(MovieActivityViewModel::class.java)

        this.movieActivityViewModel.getMovie().observe(this, Observer {
            Log.d("Debug", it.title)
        })
    }
}