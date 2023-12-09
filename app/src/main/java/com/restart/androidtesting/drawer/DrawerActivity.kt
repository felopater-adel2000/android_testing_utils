package com.restart.androidtesting.drawer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.GravityCompat
import com.restart.androidtesting.R
import com.restart.androidtesting.databinding.ActivityDrawerBinding

class DrawerActivity : AppCompatActivity() 
{
    private val TAG = "DrawerActivity"
    
    private lateinit var binding: ActivityDrawerBinding

    private lateinit var toggle: ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        binding = ActivityDrawerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpToolbar()

        setUpToggle()

    }

    private fun setUpToolbar()
    {
        if(supportActionBar == null)
        {
            Log.d(TAG, "setUpToolbar: supportActionBar is Null")
            setSupportActionBar(binding.toolBar)

            supportActionBar?.setDisplayShowTitleEnabled(false)

            supportActionBar?.setDisplayHomeAsUpEnabled(true)

            supportActionBar?.setHomeAsUpIndicator(ContextCompat.getDrawable(this, R.drawable.ic_options))
            binding.toolBar.setNavigationOnClickListener {

            }
        }
        else
        {
            Log.d(TAG, "setUpToolbar: supportActionBar is not Null")
        }
    }

    private fun setUpToggle()
    {
        toggle = object: ActionBarDrawerToggle(
            this,
            binding.root,
            binding.toolBar,
            R.string.open,
            R.string.close
        ){
            override fun onDrawerClosed(drawerView: View) {
                super.onDrawerClosed(drawerView)
                invalidateOptionsMenu()
            }

            override fun onDrawerOpened(drawerView: View) {
                super.onDrawerOpened(drawerView)
                invalidateOptionsMenu()
            }
        }


        toggle.isDrawerIndicatorEnabled = false

        val drawable = ResourcesCompat.getDrawable(
            resources, R.drawable.ic_options,
            this.theme
        )

        toggle.setHomeAsUpIndicator(ContextCompat.getDrawable(this, R.drawable.ic_options))

        binding.root.addDrawerListener(toggle)

        toggle.toolbarNavigationClickListener = View.OnClickListener {
            if(binding.root.isDrawerVisible(GravityCompat.START))
            {
                binding.root.closeDrawer(GravityCompat.START)
            }
            else
            {
                binding.root.openDrawer(GravityCompat.START)
            }
        }
        toggle.syncState()
        setNavigationItemsClickListerer()

    }


    private fun setNavigationItemsClickListerer()
    {
        binding.btnOption1.setOnClickListener {
            displayToast(R.string.option1)
            binding.root.closeDrawer(GravityCompat.START)
        }
        binding.btnOption2.setOnClickListener { displayToast(R.string.option2) }
        binding.btnOption3.setOnClickListener {
            displayToast(R.string.option3)
            binding.root.closeDrawer(GravityCompat.START)
        }
        binding.btnOption4.setOnClickListener { displayToast(R.string.option4) }
        binding.btnOption5.setOnClickListener {
            displayToast(R.string.option5)
            binding.root.closeDrawer(GravityCompat.START)
        }
    }




    private fun displayToast(@StringRes msg: Int)
    {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show()
    }

}