package com.example.champions.ui.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.SimpleItemAnimator
import com.example.champions.R
import com.example.champions.databinding.ActivityMainBinding
import com.example.champions.repository.MainRepository
import com.example.champions.ui.BaseActivity
import com.example.champions.ui.detail.DetailActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.*
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main){

    private val mainViewModel by viewModels<MainViewModel>()
    lateinit var championAdapter: ChampionAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        initUi()
        observeViewModel()
        mainViewModel.getAllChampions()
    }

    private fun initUi() {
        championAdapter = ChampionAdapter(this)
        binding.mainChampionRecyclerView.adapter = championAdapter
        binding.mainChampionRecyclerView.setHasFixedSize(true)

        val animator = binding.mainChampionRecyclerView?.itemAnimator     //리사이클러뷰 애니메이터 get
        if (animator is SimpleItemAnimator){          //아이템 애니메이커 기본 하위클래스
            animator.supportsChangeAnimations = false  //애니메이션 값 false (리사이클러뷰가 화면을 다시 갱신 했을때 뷰들의 깜빡임 방지)
        }

        binding.btnSearch.setOnClickListener {
            showSearchLayout()
        }

        binding.btnBack.setOnClickListener {
            hideSearchLayout()
        }

        binding.etSearch.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                championAdapter.searchChampions(p0.toString().lowercase(Locale.getDefault()))
            }

            override fun afterTextChanged(p0: Editable?) {
            }

        })
    }

    private fun observeViewModel() {
        mainViewModel.apiResult.observe(this, Observer { championData ->
            championData?.let { it -> championAdapter.setInitItemData(it) }
        })

        mainViewModel.mainActivityException.observe(this, Observer {
            when(it){
                is Exception -> {
                    Toast.makeText(this, "api 호출에 실패했습니다.", Toast.LENGTH_SHORT).show()
                }
            }
        })

        mainViewModel.championIdForIntent.observe(this, Observer {it ->
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra(DetailActivity.CHAMPION_ID_KEY, it)
            startActivity(intent)
        })
    }

    private fun showSearchLayout(){
        binding.initLayout.visibility = View.GONE
        binding.searchLayout.visibility = View.VISIBLE
        binding.etSearch.requestFocus()
    }

    private fun hideSearchLayout(){
        binding.searchLayout.visibility = View.GONE
        binding.etSearch.setText("")
        binding.initLayout.visibility = View.VISIBLE
    }
}