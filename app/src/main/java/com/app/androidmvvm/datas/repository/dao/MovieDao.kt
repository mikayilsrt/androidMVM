package com.app.androidmvvm.datas.repository.dao

import androidx.room.*
import com.app.androidmvvm.datas.models.Movie

@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(movie: Movie) : Unit

    @Update(onConflict = OnConflictStrategy.IGNORE)
    suspend fun update(movie: Movie) : Unit

    @Delete
    suspend fun delete(movie: Movie) : Unit

    @Query("SELECT * FROM movie_table WHERE id = :id")
    fun getMovieById(id: Int) : Movie

    @Query("SELECT * FROM movie_table")
    fun getAllMovies(): List<Movie>

}