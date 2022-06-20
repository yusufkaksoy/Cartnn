package com.example.rclean.entities.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CharactersRemoteKey(
    @PrimaryKey(autoGenerate = false)
    val id: String,
    val prev: Int?,
    val next: Int?
)
