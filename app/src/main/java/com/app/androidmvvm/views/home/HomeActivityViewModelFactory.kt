package com.app.androidmvvm.views.home

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.IllegalArgumentException

@Suppress("UNCHECKED_CAST")
class HomeActivityViewModelFactory(private val context: AppCompatActivity) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeActivityModelView::class.java)) {
            return HomeActivityModelView(context) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}