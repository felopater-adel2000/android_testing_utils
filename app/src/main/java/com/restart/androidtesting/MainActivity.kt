package com.restart.androidtesting

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.restart.androidtesting.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity()
{
    private val TAG = "MainActivity"

    private lateinit var binding: ActivityMainBinding

    private var displayNumber = 0
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.ivArrowUp.setOnClickListener {
            displayNumber++

            binding.tvText.text = "displayNumber: $displayNumber"
        }
    }
}