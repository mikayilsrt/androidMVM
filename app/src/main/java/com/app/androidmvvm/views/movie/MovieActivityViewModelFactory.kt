package com.app.androidmvvm.views.movie

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.IllegalArgumentException

@Suppress("UNCHECKED_CAST")
class MovieActivityViewModelFactory(private val context: AppCompatActivity, private val id : Int) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MovieActivityViewModel::class.java)) {
            return MovieActivityViewModel(context, id) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}