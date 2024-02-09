package com.restart.androidtesting

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.annotation.VisibleForTesting
import androidx.test.espresso.idling.CountingIdlingResource
import com.restart.androidtesting.databinding.ActivityMainBinding
import java.util.concurrent.atomic.AtomicBoolean

class MainActivity : AppCompatActivity() {
    private val TAG = "MainActivity"

    private lateinit var binding: ActivityMainBinding

    //private val idlingResource = SimpleIdlingResource()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.bntChangeText.setOnClickListener {
            if (binding.etText.text.isNotEmpty()) {
                binding.tvTitle.text = "Waiting For Message...."
                EspressoIdlingResource.increment()
                Handler(Looper.getMainLooper()).postDelayed({
                    binding.tvTitle.text = binding.etText.text.toString().trim()
                    EspressoIdlingResource.decrement()
                }, 2000)
            }
        }



    }


    /*@VisibleForTesting
    fun getIdlingResource() = idlingResource*/
}