package com.example.champions.ui.detail

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import com.example.champions.R
import com.example.champions.databinding.FragmentTargetImageBinding
import com.example.champions.util.getSkinImageUrl
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TargetImageDialog(val championId : String, val skinNum : Int, val skinName : String) : DialogFragment() {

    lateinit var binding : FragmentTargetImageBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_target_image, container, false)

        binding.championId = championId
        binding.skinNum = skinNum
        binding.skinName = skinName

        return binding.root
    }
}