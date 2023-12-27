package com.example.daggerhilt

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.daggerhilt.adapter.TvShowAdapter
import com.example.daggerhilt.databinding.ActivityMainBinding
import com.example.daggerhilt.viewmodel.TvShowViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val tvShowViewModel: TvShowViewModel by viewModels()
    private lateinit var tvShowAdapter: TvShowAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        setUpRv()
    }

    private fun setUpRv() {
        tvShowAdapter = TvShowAdapter()
        binding.tvShowRecyclerView.apply {
            adapter = tvShowAdapter
            layoutManager = LinearLayoutManager(
                this@MainActivity, LinearLayoutManager.HORIZONTAL, false
            )
            setHasFixedSize(true)
        }

        tvShowViewModel.responseTvShow.observe(this) { listTvShow ->
            tvShowAdapter.submitList(listTvShow)
        }
    }
}