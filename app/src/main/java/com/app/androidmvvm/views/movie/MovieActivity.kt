package com.app.androidmvvm.views.movie

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.app.androidmvvm.R
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.transition.DrawableCrossFadeFactory
import kotlinx.android.synthetic.main.activity_movie.*

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

        val factory : DrawableCrossFadeFactory = DrawableCrossFadeFactory.Builder().setCrossFadeEnabled(true).build()

        this.movieActivityViewModelFactory = MovieActivityViewModelFactory(this, movieIdExtra)
        this.movieActivityViewModel = ViewModelProvider(this, this.movieActivityViewModelFactory).get(MovieActivityViewModel::class.java)

        this.movieActivityViewModel.getMovie().observe(this, Observer {
            _title.text = it.title

            Glide
                .with(this)
                .load("https://image.tmdb.org/t/p/w500" + it.poster_path)
                .transition(DrawableTransitionOptions.withCrossFade(factory))
                .transform(RoundedCorners(17))
                .into(_moviePoster)
        })
    }
}