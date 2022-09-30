package com.example.mvvm.repository

import com.example.mvvm.rest.RetrofitServices

class MainRepository constructor(
    private val retrofitServices: RetrofitServices
) {
    fun getAllLives() = retrofitServices.getAllLives()
}