package com.hlink.mvvmroomretorfit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider

class MainActivity : AppCompatActivity() {

    lateinit var mainViewModel: MainViewModel

    lateinit var textviewCount: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        lifecycle.addObserver(Observer())

        mainViewModel =
            ViewModelProvider(this, MainViewModelFactory(10)).get(MainViewModel::class.java)

        Log.d("Main", "Activity - OnCreate")
        textviewCount = findViewById(R.id.textViewCount)

        setText()
    }

    private fun setText() {
        textviewCount.setText(mainViewModel.count.toString())
    }

    fun increment(view: View) {
        mainViewModel.increment()
        setText()
    }
}