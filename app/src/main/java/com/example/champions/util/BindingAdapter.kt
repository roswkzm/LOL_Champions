package com.example.champions.util

import android.graphics.Color
import android.widget.ImageView
import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.champions.R
import com.example.champions.ui.detail.SkinCarouselAdapter
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import com.jackandphantom.carouselrecyclerview.CarouselRecyclerview

@BindingAdapter("glide_default")
fun bindGlideDefault(view : ImageView, url : String){
    Glide.with(view.context).load(url).into(view)
}

@BindingAdapter("set_chip_adapter")
fun ChipGroup.bindChip(chip : List<String>?){
    chip?.forEach {
        val newChip : Chip = Chip(context).apply {
            text = it
            isCheckable = false
            isCloseIconVisible = false
            setTextAppearanceResource(R.style.ChipTextStyle)
            setChipBackgroundColorResource(R.color.purple)
        }
        addView(newChip)
    }
}