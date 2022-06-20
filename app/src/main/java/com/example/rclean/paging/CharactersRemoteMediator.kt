package com.example.rclean.paging

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import com.example.rclean.entities.local.CharactersDao
import com.example.rclean.entities.local.CharactersRemoteKey
import com.example.rclean.network.api.ApiService
import com.example.rclean.network.model.RickMorty
import java.io.InvalidObjectException

@ExperimentalPagingApi
class CharactersRemoteMediator(
    private val charactersDao: CharactersDao,
    private val apiService: ApiService,
    private val initialPage: Int = 1
) : RemoteMediator<Int, RickMorty>() {
    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, RickMorty>
    ): MediatorResult {
        return try {
            val page = when (loadType) {
                LoadType.APPEND -> {
                    val remoteKey = getLastRemoteKey(state)
                        ?: throw InvalidObjectException("No remote key found")
                    remoteKey.next ?: return MediatorResult.Success(endOfPaginationReached = true)

                }
                LoadType.PREPEND -> {
                    return MediatorResult.Success(endOfPaginationReached = true)
                }
                LoadType.REFRESH -> {
                    val remoteKey = getClosestRemoteKey(state)
                    remoteKey?.next?.minus(1) ?: initialPage

                }
            }

            val response = apiService.getAllCharacters(page)

            val endOfPagination = response.body()?.results?.size!! < state.config.pageSize

            if (response.isSuccessful) {
                response.body()?.results?.let {
                    if (loadType == LoadType.REFRESH) {
                        charactersDao.deleteCharacters()
                        charactersDao.deleteAllRemoteKeys()

                    }

                    val prev = if (page == initialPage) null else page - 1
                    val next = if (endOfPagination) null else page + 1

                    val list = response.body()?.results?.map {
                        CharactersRemoteKey(it.name, prev, next)
                    }

                    if (list != null) {
                        charactersDao.insertAllRemoteKeys(list)
                    }
                    charactersDao.insertCharacters(it)

                }
                MediatorResult.Success(endOfPagination)
            } else {
                MediatorResult.Success(endOfPaginationReached = true)
            }

        } catch (e: Exception) {
            return MediatorResult.Error(e)
        }

        return MediatorResult.Success(endOfPaginationReached = true)

    }

    suspend fun getClosestRemoteKey(state: PagingState<Int, RickMorty>): CharactersRemoteKey? {
        return state.anchorPosition?.let {
            state.closestItemToPosition(it)?.let {
                charactersDao.gettAllRemoteKeys(it.name)
            }
        }
    }

    suspend fun getLastRemoteKey(state: PagingState<Int, RickMorty>): CharactersRemoteKey? {
        return state.lastItemOrNull()?.let {
            charactersDao.gettAllRemoteKeys(it.name)
        }
    }

}
