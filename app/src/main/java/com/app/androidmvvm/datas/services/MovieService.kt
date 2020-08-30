package com.app.androidmvvm.datas.services

import com.app.androidmvvm.datas.models.Movie
import com.app.androidmvvm.datas.models.PopularMoviesFromService
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieService {

    @GET("movie/popular")
    fun getPopularMovies(
        @Query("api_key") api_key : String = ServiceObject.api_key
    ) : Call<PopularMoviesFromService<List<Movie>>>

}