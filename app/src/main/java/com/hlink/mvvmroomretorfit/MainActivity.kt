package com.hlink.mvvmroomretorfit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.hlink.mvvmroomretorfit.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    lateinit var mainViewModel: MainViewModel

    lateinit var liveDataViewModel: LiveDataViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        lifecycle.addObserver(Observer())

        mainViewModel =
            ViewModelProvider(this, MainViewModelFactory(10)).get(MainViewModel::class.java)

        liveDataViewModel =
            ViewModelProvider(this).get(LiveDataViewModel::class.java)

        Log.d("Main", "Activity - OnCreate")


        setText()

        observedLiveData()
    }

    private fun observedLiveData() {
        liveDataViewModel.factLiveData.observe(this, Observer {
            Log.d("MAIN", it)
        })
    }

    private fun setText() {
        binding.textViewCount.setText(mainViewModel.count.toString())
    }

    fun increment(view: View) {
        mainViewModel.increment()
        setText()
    }

    fun liveDataTest(view: View) {
        liveDataViewModel.factLiveData.value = "Hello text changed"
    }
}