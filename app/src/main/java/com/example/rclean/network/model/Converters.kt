package com.example.rclean.network.model

import androidx.room.TypeConverter
import java.security.acl.LastOwnerException

class Converters {

    @TypeConverter
    fun fromLocation(location: Location):String{
        return location.name
    }
    @TypeConverter
    fun toLocation(name:String):Location{
        return Location(name,name)

    }

}