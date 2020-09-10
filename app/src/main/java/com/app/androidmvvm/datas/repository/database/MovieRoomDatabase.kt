package com.app.androidmvvm.datas.repository.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.app.androidmvvm.datas.models.Movie
import com.app.androidmvvm.datas.repository.dao.MovieDao

@Database(entities = [Movie::class], version = 1, exportSchema = false)
abstract class MovieRoomDatabase : RoomDatabase() {

    abstract fun movieDao(): MovieDao

    companion object {
        @Volatile
        private var INSTANCE : MovieRoomDatabase? = null

        fun getDatabase(context: Context) : MovieRoomDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null)
                return tempInstance

            synchronized(this) {
                val instance = Room
                    .databaseBuilder(context.applicationContext, MovieRoomDatabase::class.java, "movie_database")
                    .build()
                INSTANCE = instance

                return instance
            }
        }
    }

}