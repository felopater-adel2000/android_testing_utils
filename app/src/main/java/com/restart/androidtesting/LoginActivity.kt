package com.restart.androidtesting

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.core.content.ContextCompat
import androidx.lifecycle.MutableLiveData
import com.restart.androidtesting.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity()
{
    private val TAG = "LoginActivity"

    private val validateData = MutableLiveData<Boolean>()

    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        observedata()
        initViews()

    }

    private fun observedata()
    {
        validateData.observe(this){
            it?.let {
                if(it)
                {
                    binding.tvLogin.isEnabled = true
                    binding.tvLogin.background = ContextCompat.getDrawable(this, R.drawable.background_active_button)
                }
                else
                {
                    binding.tvLogin.isEnabled = false
                    binding.tvLogin.background = ContextCompat.getDrawable(this, R.drawable.background_inactive_button)
                }
            }
        }
    }

    private fun initViews()
    {
        val textWatcher = object: TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
                validateData()
            }

        }

        binding.etEmail.addTextChangedListener(textWatcher)
        binding.etPassword.addTextChangedListener(textWatcher)
    }

    private fun validateData()
    {
        if(!binding.etEmail.text.contains("@"))
        {
            validateData.value = false
        }
        else if(binding.etPassword.text.toString().length < 6)
        {
            validateData.value = false
        }
        else
        {
            validateData.value = true
        }
    }
}