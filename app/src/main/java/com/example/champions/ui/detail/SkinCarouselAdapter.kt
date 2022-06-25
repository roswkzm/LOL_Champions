package com.example.champions.ui.detail

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.RecyclerView
import com.example.champions.R
import com.example.champions.databinding.ItemSkinBinding
import com.example.champions.model.ChampionDetail

class SkinCarouselAdapter(
    private val viewModel : DetailViewModel,
    private val skinList : List<ChampionDetail.Skin>,
    private val id : String)
    : RecyclerView.Adapter<SkinCarouselAdapter.CustomViewHolder>() {

    class CustomViewHolder(val binding : ItemSkinBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SkinCarouselAdapter.CustomViewHolder {
        val binding = DataBindingUtil.inflate<ItemSkinBinding>(LayoutInflater.from(parent.context), R.layout.item_skin, parent, false)
        return CustomViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SkinCarouselAdapter.CustomViewHolder, position: Int) {
        holder.binding.skin = skinList[position]
        holder.binding.championId = id
        holder.binding.viewModel = viewModel
    }

    override fun getItemCount(): Int {
        return skinList.size
    }
}