package com.restart.androidtesting

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncDifferConfig
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListUpdateCallback
import androidx.recyclerview.widget.RecyclerView
import com.restart.androidtesting.databinding.ItemRecyclerBinding

class RecyclerAdapter(private val itemCallbacks: ItemCallbacks) : RecyclerView.Adapter<RecyclerView.ViewHolder>()
{
    private val DIFF_CALLBACK = object: DiffUtil.ItemCallback<RecyclerModel>(){
        override fun areItemsTheSame(oldItem: RecyclerModel, newItem: RecyclerModel): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: RecyclerModel, newItem: RecyclerModel): Boolean {
            return oldItem == newItem
        }
    }

    private val differ = AsyncListDiffer(
        CustomRecyclerChangeCallback(this),
        AsyncDifferConfig.Builder(DIFF_CALLBACK).build()
    )

    inner class CustomRecyclerChangeCallback(val adapter: RecyclerAdapter): ListUpdateCallback {
        override fun onInserted(position: Int, count: Int) {
            adapter.notifyItemRangeChanged(position, count)
        }

        override fun onRemoved(position: Int, count: Int) {
            adapter.notifyDataSetChanged()
        }

        override fun onMoved(fromPosition: Int, toPosition: Int) {
            adapter.notifyDataSetChanged()
        }

        override fun onChanged(position: Int, count: Int, payload: Any?) {
            adapter.notifyItemRangeChanged(position, count, payload)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return CustomRecyclerViewHolder(
            ItemRecyclerBinding.inflate(LayoutInflater.from(parent.context), parent, false),
            itemCallbacks
        )
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as CustomRecyclerViewHolder).bin(differ.currentList[position])
    }

    fun submitList(list: List<RecyclerModel>)
    {
        differ.submitList(list)
    }

    class CustomRecyclerViewHolder(
        private val binding: ItemRecyclerBinding,
        private val itemCallbacks: ItemCallbacks
    ): RecyclerView.ViewHolder(binding.root)
    {
        fun bin(item: RecyclerModel) = with(binding.root){
            binding.apply {
                tvText.text = context.getString(R.string.item_text, adapterPosition)

                binding.btn.setOnClickListener {
                    itemCallbacks.onClickButton(adapterPosition)
                }

                binding.root.setOnClickListener {
                    itemCallbacks.onItemClick(adapterPosition)
                }

                cb.isChecked = adapterPosition % 2 == 0
            }
        }
    }

    interface ItemCallbacks
    {
        fun onClickButton(pos: Int)

        fun onItemClick(position: Int)
    }
}