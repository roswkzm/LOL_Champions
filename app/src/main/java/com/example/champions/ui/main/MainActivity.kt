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

        binding.editText.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                Log.d("ㅎㅇㅎㅇ", p0.toString())
            }

            override fun afterTextChanged(p0: Editable?) {

            }

        })
    }

    private fun observeViewModel() {
        mainViewModel.apiResult.observe(this, Observer {
            mainViewModel.apiResult.value?.let { it1 -> championAdapter.getItemData(it1) }
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
}