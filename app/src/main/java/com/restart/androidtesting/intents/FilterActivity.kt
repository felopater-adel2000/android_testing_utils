package com.restart.androidtesting.intents

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.restart.androidtesting.R
import com.restart.androidtesting.databinding.ActivityFilterBinding
import java.util.Random

class FilterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFilterBinding
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        binding = ActivityFilterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSetResult.setOnClickListener {
            val result = Random().nextInt(100)

            setResult(Activity.RESULT_OK, Intent().putExtra("data", result.toString()))
            finish()
        }
    }
}