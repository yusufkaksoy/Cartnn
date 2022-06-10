package com.example.rclean.entities.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Director(
    @PrimaryKey(autoGenerate = false)
    //String veri olduğu için autogenerateye ihtiyacımız yok.
    val directorName : String,
    val schoolName : String
)