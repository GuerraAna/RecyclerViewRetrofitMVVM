package com.example.mvvm.rest

import com.example.mvvm.model.Live
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface RetrofitServices {

    @GET(OP_GET_LIST_LIVES)
    fun getAllLives(): Call<List<Live>>

    companion object {
        const val OP_GET_LIST_LIVES = "lista-lives.json"

        private val retrofitServices: RetrofitServices by lazy {
            val retrofit = Retrofit.Builder()
                .baseUrl("https://d3c0cr0sze1oo6.cloudfront.net/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            retrofit.create(RetrofitServices::class.java)
        }

        fun getInstance(): RetrofitServices {
            return retrofitServices
        }
    }
}