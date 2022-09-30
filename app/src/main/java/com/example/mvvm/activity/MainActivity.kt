package com.example.mvvm.activity

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.mvvm.adapter.MainAdapter
import com.example.mvvm.databinding.ActivityMainBinding
import com.example.mvvm.R
import com.example.mvvm.databinding.ActivityMainBinding.inflate
import com.example.mvvm.repository.MainRepository
import com.example.mvvm.rest.RetrofitServices
import com.example.mvvm.viewmodel.MainViewModel
import com.example.mvvm.viewmodel.ViewModelFactory

class MainActivity : AppCompatActivity() {

    private val TAG = "MainActivity"
    private lateinit var binding: ActivityMainBinding
    lateinit var viewModel: MainViewModel
    private val retrofitServices = RetrofitServices.getInstance()

    private val adapter = MainAdapter { live ->
        openLink(live.link)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = inflate(layoutInflater)
        setContentView(binding.root)

        getViewModel()
        setUpAdapter()
    }

    private fun setUpAdapter() {
        binding.listOfLives.adapter = adapter
    }

    private fun getViewModel() {
        viewModel = ViewModelProvider(
            this,
            ViewModelFactory(MainRepository(retrofitServices))
        ).get(MainViewModel::class.java)
    }

    override fun onStart() {
        super.onStart()

        setUpObserve()
    }

    private fun setUpObserve() {
        viewModel.liveList.observe(this, Observer {
            Log.d(TAG, "onCreate: $it")
            adapter.setLiveList(it)
        })

        viewModel.errorMessage.observe(this, Observer {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        })
    }

    override fun onResume() {
        super.onResume()
        viewModel.getAllLives()
    }

    private fun openLink(link: String) {
        val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(link))
        startActivity(browserIntent)
    }

}
