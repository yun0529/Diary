package com.example.mydiary.src.main.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mydiary.databinding.ItemListContentBinding

private lateinit var contentBinding : ItemListContentBinding

class ListAdapter(private val lists : ArrayList<Content>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        contentBinding = ItemListContentBinding.inflate(LayoutInflater.from(parent.context),parent, false)
        return ViewHolder(contentBinding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder){
            is ViewHolder -> holder.bind(lists[position])
        }
    }

    override fun getItemCount(): Int {
        return lists.size
    }

    class ViewHolder(private val binding: ItemListContentBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data : Content){
            binding.itemListTvTitle.text = data.title
        }

    }

}