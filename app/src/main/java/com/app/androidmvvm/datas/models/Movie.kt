package com.app.androidmvvm.datas.models

class Movie(
    val id : Int,
    val title : String,
    val original_title : String,
    val poster_path : String? = null
)

class PopularMoviesFromService<T>(
    val page : Int? = 0,
    val results : T
)