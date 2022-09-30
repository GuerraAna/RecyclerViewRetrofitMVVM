package com.example.mvvm.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mvvm.repository.MainRepository

class ViewModelFactory constructor(
    private val repository: MainRepository
): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if(modelClass.isAssignableFrom(
                MainViewModel::class.java
        )) {
            MainViewModel(this.repository) as T
        } else {
            throw IllegalAccessException(ILEGAL_ACCESS_EXCEPTION)
        }
    }

    companion object {
        const val ILEGAL_ACCESS_EXCEPTION = "ViewModel Not Found"
    }
}