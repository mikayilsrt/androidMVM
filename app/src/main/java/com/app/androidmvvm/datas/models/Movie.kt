package com.app.androidmvvm.datas.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movie_table")
open class Movie(
    @PrimaryKey
    @ColumnInfo(name = "id") val id : Int,
    @ColumnInfo(name = "title") val title : String,
    @ColumnInfo(name = "original_title") val original_title : String,
    @ColumnInfo(name = "poster_path") val poster_path : String? = null
)

class PopularMoviesFromService<T>(
    val page : Int? = 0,
    val results : T
)