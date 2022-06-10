package com.example.rclean.entities.relations

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.example.rclean.entities.data.Student
import com.example.rclean.entities.data.Subject

data class SubjectWithStudents(
    @Embedded val subject: Subject,
    @Relation(
        parentColumn = "subjectName",
        entityColumn = "studentName",
        associateBy = Junction(StudentSubjectCrossRef::class)
    )
    val students: List<Student>
)
