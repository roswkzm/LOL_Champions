package com.example.champions.ui.main

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.champions.R
import com.example.champions.databinding.ItemMainRecyclerviewBinding
import com.example.champions.model.Champion
import com.example.champions.ui.detail.DetailActivity

class ChampionAdapter(private val viewModelStoreOwner: ViewModelStoreOwner) : RecyclerView.Adapter<ChampionAdapter.CustomViewHolder>() {
    var championData : List<Champion> = listOf()

    class CustomViewHolder(val binding : ItemMainRecyclerviewBinding) : RecyclerView.ViewHolder(binding.root){
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChampionAdapter.CustomViewHolder {
        val binding = DataBindingUtil.inflate<ItemMainRecyclerviewBinding>(LayoutInflater.from(parent.context), R.layout.item_main_recyclerview, parent, false)
        val viewModel = ViewModelProvider(viewModelStoreOwner).get(MainViewModel::class.java)
        binding.viewModel = viewModel
        return CustomViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ChampionAdapter.CustomViewHolder, position: Int) {
        holder.binding.champion = championData[position]
    }

    override fun getItemCount(): Int {
        return championData.size
    }

    fun getItemData(item : List<Champion>){
        championData = item
        notifyDataSetChanged()
    }
}