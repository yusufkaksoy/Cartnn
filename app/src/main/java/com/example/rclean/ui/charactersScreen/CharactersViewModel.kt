package com.example.rclean.ui.charactersScreen

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.*
import com.example.rclean.entities.local.CharactersDao
import com.example.rclean.network.api.ApiService
import com.example.rclean.network.model.ResponseApi
import com.example.rclean.network.model.RickMorty
import com.example.rclean.paging.CharactersRemoteMediator
import com.example.rclean.paging.RickMortyPagingSource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CharactersViewModel @Inject constructor(private val apiService: ApiService,private val charactersDao: CharactersDao):ViewModel() {

    val listData =Pager(PagingConfig(pageSize = 1)) {
        RickMortyPagingSource(apiService)
    }.flow.cachedIn(viewModelScope)




    @ExperimentalPagingApi
    val pager = Pager(PagingConfig(pageSize = 10), remoteMediator = CharactersRemoteMediator(charactersDao, apiService,1)){

        charactersDao.getAllCharactersDao()

    }.flow.cachedIn(viewModelScope)
}