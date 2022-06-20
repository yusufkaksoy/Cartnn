package com.example.rclean.entities.local

import androidx.lifecycle.LiveData
import androidx.paging.PagingSource
import androidx.room.*
import com.example.rclean.network.model.RickMorty

@Dao
interface CharactersDao {
@Insert(onConflict = OnConflictStrategy.REPLACE)
suspend fun insertCharacters(list: List<RickMorty>)


@Query("SELECT * FROM RickMorty")
fun getAllCharactersDao():PagingSource<Int,RickMorty>

@Query("DELETE  FROM RickMorty")
suspend fun  deleteCharacters()

@Insert(onConflict = OnConflictStrategy.REPLACE)
suspend fun insertAllRemoteKeys(list: List<CharactersRemoteKey>)

@Query("SELECT * FROM CHARACTERSREMOTEKEY WHERE id = :id")
suspend fun gettAllRemoteKeys(id: String): CharactersRemoteKey?

@Query("DELETE  FROM CharactersRemoteKey")
suspend fun deleteAllRemoteKeys()
}