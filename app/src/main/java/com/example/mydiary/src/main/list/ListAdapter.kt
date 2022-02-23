package com.example.mydiary.src.main.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mydiary.databinding.ItemListContentBinding
import com.example.mydiary.databinding.ItemListPicturBinding

private lateinit var contentBinding : ItemListContentBinding
private lateinit var picturBinding : ItemListPicturBinding

class ListAdapter(private val lists : ArrayList<Content>, private var change : Int) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if(change == 0){
            contentBinding = ItemListContentBinding.inflate(LayoutInflater.from(parent.context),parent, false)
            return ViewHolder(contentBinding)
        }else{
            picturBinding = ItemListPicturBinding.inflate(LayoutInflater.from(parent.context),parent, false)
            return PicViewHolder(picturBinding)
        }

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder){
            is ViewHolder -> holder.bind(lists[position])
            is PicViewHolder -> holder.bind(lists[position])
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

    class PicViewHolder(private val binding: ItemListPicturBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data : Content){
            binding.itemListTvTitle.text = data.title
        }

    }

}