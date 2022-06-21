package com.example.rclean.network.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
data class RickMorty(

    val created: String,
    val id : Int,

    val gender: String,

    val location: Location,
    val image: String,
    @PrimaryKey(autoGenerate = false)
    val name: String,
    val species: String,
    val status: String,
    val type: String,
    val url: String,


    )