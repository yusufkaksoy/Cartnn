package com.example.rclean.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.ExperimentalPagingApi
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.rclean.databinding.ActivityCharactersBinding

import com.example.rclean.ui.charactersScreen.CharactersAdapter
import com.example.rclean.ui.charactersScreen.CharactersViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CharactersActivity : AppCompatActivity() {


    private lateinit var binding: ActivityCharactersBinding
    private lateinit var mAdapter: CharactersAdapter
    private val viewModel: CharactersViewModel by viewModels()
    @ExperimentalPagingApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCharactersBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupRecyclerView()
        loadingData()

    }


    private fun setupRecyclerView() {

        mAdapter = CharactersAdapter()

        binding.homeRv.apply {
            adapter = mAdapter
            layoutManager = StaggeredGridLayoutManager(
                1, StaggeredGridLayoutManager.VERTICAL
            )
            setHasFixedSize(true)
        }

    }

    @ExperimentalPagingApi
    private fun loadingData() {
        lifecycleScope.launch {
            viewModel.pager.collect { pagingData ->
                mAdapter.submitData(pagingData)
            }

        }
    }


}



