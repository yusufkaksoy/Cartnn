package com.example.rclean.entities.local

import android.content.Context
import androidx.room.*
import com.example.rclean.network.model.Converters
import com.example.rclean.network.model.RickMorty

@Database(entities = [RickMorty::class,CharactersRemoteKey::class], version = 1)
@TypeConverters(Converters::class)
abstract class CharactersDatabase : RoomDatabase() {
    companion object {
        fun getInstance(context: Context): CharactersDatabase {
            return Room.databaseBuilder(context, CharactersDatabase::class.java, "characters_db")
                .build()
        }
    }

    abstract  fun getCharactersDao(): CharactersDao
}