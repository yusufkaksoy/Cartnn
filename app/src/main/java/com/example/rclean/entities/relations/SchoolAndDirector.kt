package com.example.rclean.entities.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.example.rclean.entities.Director
import com.example.rclean.entities.School

data class SchoolAndDirector(

    @Embedded val school: School,
    @Relation(parentColumn = "schoolName", entityColumn = "schoolName")
    val director: Director
    )
//Relation annotionu :
//one-to-one  olduğu için ekstra tabloya ihtiyacımız yok. n-to-2m olsa @Entity olurudu.
//embedded class üst sınıftaki datanın  anlık görüntülenmesini sağlar.
