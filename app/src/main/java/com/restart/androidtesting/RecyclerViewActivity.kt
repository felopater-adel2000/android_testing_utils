package com.restart.androidtesting

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.restart.androidtesting.databinding.ActivityRecyclerViewBinding

class RecyclerViewActivity : AppCompatActivity(), RecyclerAdapter.ItemCallbacks
{
    private val TAG = "RecyclerViewActivity"

    private lateinit var binding: ActivityRecyclerViewBinding

    private val data = ArrayList<RecyclerModel>()
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        binding = ActivityRecyclerViewBinding.inflate(layoutInflater)
        setContentView(binding.root)
        fillData()

        initAdapter()
    }

    private fun initAdapter()
    {
        val adapter = RecyclerAdapter(this)
        binding.rv.adapter = adapter
        adapter.submitList(data)
    }

    private fun fillData()
    {
        for(i in 0 .. 100)
        {
            data.add(RecyclerModel(i, "item: $i"))
        }
    }

    override fun onClickButton(pos: Int) {
    }

    override fun onItemClick(position: Int) {
        binding.tvResult.text = getString(R.string.item_text, position)
    }
}