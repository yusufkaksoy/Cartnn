package com.example.rclean.entities.relations

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.example.rclean.entities.data.Student
import com.example.rclean.entities.data.Subject

data class StudentWithSubjects(
    @Embedded val student: Student,
    @Relation(
        parentColumn = "studentName",
        entityColumn = "subjectName",
        associateBy = Junction(StudentSubjectCrossRef::class)
    )
    val subjects: List<Subject>
)
