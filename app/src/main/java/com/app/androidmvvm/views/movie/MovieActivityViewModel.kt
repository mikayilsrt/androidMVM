package com.app.androidmvvm.views.movie

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.app.androidmvvm.datas.models.Movie
import com.app.androidmvvm.datas.repository.MovieRepository
import com.app.androidmvvm.datas.repository.dao.MovieDao
import com.app.androidmvvm.datas.repository.database.MovieRoomDatabase

class MovieActivityViewModel(context: AppCompatActivity, id : Int) : ViewModel() {

    private lateinit var movieRepository : MovieRepository

    init {
        val movieDao : MovieDao = MovieRoomDatabase.getDatabase(context).movieDao()
        movieRepository = MovieRepository(movieDao)

        this.movieRepository.callMovie(id)
        this.movieRepository.getMovie().observe(context, Observer {
            this._movie.value = it
        })
    }

    private val _movie : MutableLiveData<Movie> = MutableLiveData()
    fun getMovie() : LiveData<Movie> {
        return this._movie
    }

}