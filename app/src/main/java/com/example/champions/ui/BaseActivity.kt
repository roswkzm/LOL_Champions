package com.example.champions.ui

import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

abstract class BaseActivity<T : ViewDataBinding>(
    @LayoutRes private val layoutId : Int
) : AppCompatActivity() {
    protected val binding : T by lazy{DataBindingUtil.setContentView(this, layoutId)}
}