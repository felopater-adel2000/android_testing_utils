package com.restart.androidtesting.intents

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import com.restart.androidtesting.R
import com.restart.androidtesting.databinding.ActivityStartIntentBinding

class StartIntentActivity : AppCompatActivity()
{
    private val TAG = "StartIntentActivity"

    private lateinit var binding: ActivityStartIntentBinding

    private val resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
        if(it.resultCode == Activity.RESULT_OK)
        {
            binding.tvResult.text = it.data?.getStringExtra("data")
        }
    }

    private val requestMultiPermission: ActivityResultLauncher<Array<String>> = registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()){

    }
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        binding = ActivityStartIntentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnGetResult.setOnClickListener {
            resultLauncher.launch(
                Intent(this, FilterActivity::class.java)
            )
        }

        binding.btnCall.setOnClickListener {
            val intent = Intent(Intent.ACTION_DIAL)
            intent.data = Uri.parse("tel: 01277811402")

            val permission = ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED


            startActivity(intent)
        }
    }
}