package com.example.champions.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.example.champions.R
import com.example.champions.databinding.ActivityDetailBinding
import com.example.champions.ui.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailActivity : BaseActivity<ActivityDetailBinding>(R.layout.activity_detail) {

    private val detailViewModel by viewModels<DetailViewModel>()
    private val skinCarouselAdapter by lazy { SkinCarouselAdapter(detailViewModel ,detailViewModel.apiResult.value?.skins!!, detailViewModel.apiResult.value?.id!!) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.viewModel = detailViewModel
        val championId = intent.getStringExtra(CHAMPION_ID_KEY)

        observeViewModel()
        detailViewModel.getChampionDetail(championId!!)
    }

    fun setSkinRecyclerView(){
        with(binding.carouselRecyclerview){
            adapter = skinCarouselAdapter
            set3DItem(true)
            setInfinite(true)
            setIntervalRatio(0.6F)
            setAlpha(true)
            isNestedScrollingEnabled = false
        }
    }

    fun observeViewModel(){
        detailViewModel.apiResult.observe(this, Observer { championDetail ->
            binding.championDetail = championDetail
            setSkinRecyclerView()
        })

        detailViewModel.detailActivityException.observe(this, Observer {
            Toast.makeText(this, "Api를 정상적으로 불러오지 못했습니다.", Toast.LENGTH_SHORT).show()
        })

        detailViewModel.showSkinDetailDialog.observe(this, Observer { it ->
            TargetImageDialog(it.first, it.second, it.third).show(supportFragmentManager, "Hello")
        })
    }

    companion object{
        const val CHAMPION_ID_KEY = "CHAMPION_ID_KEY"
    }
}