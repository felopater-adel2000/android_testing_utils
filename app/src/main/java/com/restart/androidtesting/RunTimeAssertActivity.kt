package com.restart.androidtesting

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.restart.androidtesting.databinding.ActivityRunTimeAssertBinding

class RunTimeAssertActivity : AppCompatActivity()
{

    private lateinit var binding: ActivityRunTimeAssertBinding

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        binding = ActivityRunTimeAssertBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnCheck.setOnClickListener {
            assert(binding.etText.text.toString() != "Hello")

            Toast.makeText(this, binding.etText.text.toString().trim(), Toast.LENGTH_LONG).show()
        }
    }
}