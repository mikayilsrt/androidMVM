package com.app.androidmvvm.views.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.app.androidmvvm.datas.models.Movie

class HomeActivityModelView : ViewModel() {

    private var _popularMovies : MutableLiveData<List<Movie>> = MutableLiveData()

    init {
        val movie1 = Movie(0, "title 1", "original title 1", "poster.png")
        val movie2 = Movie(0, "title 2", "original title 2", "poster.png")
        val movie3 = Movie(0, "title 3", "original title 3", "poster.png")
        val movie4 = Movie(0, "title 4", "original title 4", "poster.png")

        _popularMovies.postValue(listOf(movie1, movie2, movie3, movie4))
    }

    fun getPopularMovies(): LiveData<List<Movie>> {
        return this._popularMovies
    }

}