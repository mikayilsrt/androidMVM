package com.app.androidmvvm.views.home

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.*
import com.app.androidmvvm.datas.models.Movie
import com.app.androidmvvm.datas.repository.MovieRepository
import com.app.androidmvvm.datas.repository.dao.MovieDao
import com.app.androidmvvm.datas.repository.database.MovieRoomDatabase

class HomeActivityViewModel(context: AppCompatActivity) : ViewModel() {

    private lateinit var movieRepository : MovieRepository

    private var _popularMovies : MutableLiveData<List<Movie>> = MutableLiveData()

    init {
        val movieDao : MovieDao = MovieRoomDatabase.getDatabase(context).movieDao()
        movieRepository = MovieRepository(movieDao)

        movieRepository.callPopularMovies()
        movieRepository.getPopularMovies().observe(context, Observer {
            this._popularMovies.value = it
        })
    }

    fun getPopularMovies(): LiveData<List<Movie>> {
        return this._popularMovies
    }

}