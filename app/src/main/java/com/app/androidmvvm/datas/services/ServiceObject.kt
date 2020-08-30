package com.app.androidmvvm.datas.services

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ServiceObject {

    const val api_key : String = ""

    val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("https://api.themoviedb.org/3/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

}