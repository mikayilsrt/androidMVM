package com.app.androidmvvm.views.home

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.app.androidmvvm.datas.models.Movie
import com.app.androidmvvm.datas.repository.MovieRepository

class HomeActivityViewModel(context: AppCompatActivity) : ViewModel() {

    private val movieRepository : MovieRepository = MovieRepository()

    private var _popularMovies : MutableLiveData<List<Movie>> = MutableLiveData()

    init {
        movieRepository.callPopularMovies()
        movieRepository.getPopularMovies().observe(context, Observer { movies ->
            this._popularMovies.value = movies
        })
    }

    fun getPopularMovies(): LiveData<List<Movie>> {
        return this._popularMovies
    }

}