package com.app.androidmvvm.datas.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.app.androidmvvm.datas.models.Movie
import com.app.androidmvvm.datas.models.PopularMoviesFromService
import com.app.androidmvvm.datas.repository.dao.MovieDao
import com.app.androidmvvm.datas.services.MovieService
import com.app.androidmvvm.datas.services.ServiceObject
import kotlinx.coroutines.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieRepository(private val movieDao: MovieDao) {

    private val service : MovieService = ServiceObject.retrofit.create(MovieService::class.java)

    private val _popularMovies : MutableLiveData<List<Movie>> = MutableLiveData()
    fun getPopularMovies() : LiveData<List<Movie>> {
        return this._popularMovies
    }

    private val _movie : MutableLiveData<Movie> = MutableLiveData()
    fun getMovie() : LiveData<Movie> {
        return this._movie
    }

    fun callPopularMovies() {
        GlobalScope.launch {
            val movieList : List<Movie> = movieDao.getAllMovies()
            if (movieList.isNotEmpty()) {
                withContext(Dispatchers.Main) {
                    this@MovieRepository._popularMovies.value = movieList
                }
            } else {
                service.getPopularMovies().enqueue(object: Callback<PopularMoviesFromService<List<Movie>>> {
                    override fun onFailure(
                        call: Call<PopularMoviesFromService<List<Movie>>>,
                        t: Throwable
                    ) = Unit

                    override fun onResponse(
                        call: Call<PopularMoviesFromService<List<Movie>>>,
                        response: Response<PopularMoviesFromService<List<Movie>>>
                    ) {
                        if (response.isSuccessful) {
                            this@MovieRepository._popularMovies.value = response.body()?.results
                            GlobalScope.launch {
                                response.body()?.results?.forEach {
                                    movieDao.insert(it)
                                }
                            }
                        }
                    }
                })
            }
        }
    }

    fun callMovie(id: Int) {
        service.getMovie(id).enqueue(object: Callback<Movie> {
            override fun onFailure(call: Call<Movie>, t: Throwable) = Unit

            override fun onResponse(call: Call<Movie>, response: Response<Movie>) {
                if (response.isSuccessful)
                    this@MovieRepository._movie.value = response.body()
            }
        })
    }

}