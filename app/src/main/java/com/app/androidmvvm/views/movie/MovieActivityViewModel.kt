package com.app.androidmvvm.views.movie

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.app.androidmvvm.datas.models.Movie
import com.app.androidmvvm.datas.repository.MovieRepository

class MovieActivityViewModel(context: AppCompatActivity, id : Int) : ViewModel() {

    private val movieRepository : MovieRepository = MovieRepository()

    private val _movie : MutableLiveData<Movie> = MutableLiveData()
    fun getMovie() : LiveData<Movie> {
        return this._movie
    }

    init {
        this.movieRepository.callMovie(id)
        this.movieRepository.getMovie().observe(context, Observer {
            this._movie.value = it
        })
    }

}