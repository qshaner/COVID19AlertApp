package com.example.covid19notification.ui.news

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.covid19notification.R
import android.util.Log


class NewsActivity : AppCompatActivity() {
    private val tag = "NewsActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(tag, "onCreate() called.")
        setContentView(R.layout.activity_news)
    }

    override fun onResume() {
        super.onResume()
        Log.d(tag, "onResume called.")
    }

    override fun onPause() {
            super.onPause()
        Log.d(tag, "onPause() called.")
    }

    override fun onStop() {
        super.onStop()
        Log.d(tag, "onStop() called.")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(tag, "onDestroy() called. ")
    }


}