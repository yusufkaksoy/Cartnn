package com.example.rclean.entities.relations

import com.example.rclean.entities.data.Student



import androidx.room.Embedded
import androidx.room.Relation
import com.example.rclean.entities.data.School

data class SchoolWithStudents(
    @Embedded
    val school: School,
    @Relation(
        parentColumn = "schoolName",
        entityColumn = "schoolName"
    )
    val students: List<Student>




    )
