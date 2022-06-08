package com.example.rclean

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.example.rclean.entities.Director
import com.example.rclean.entities.School
import com.example.rclean.entities.Student
import com.example.rclean.entities.Subject
import com.example.rclean.entities.relations.StudentSubjectCrossRef
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val dao = SchoolDatabase.getInstance(this).schoolDao

        val directors = listOf(
            Director("Mahmut Demir", "Ataturk Lisesi"),
            Director("Ekrem Sade", "FSM Lisesi"),
            Director("Kenan Doğulu", "Galatasaray Lisesi")
        )
        val schools = listOf(
            School("Ataturk Lisesi"),
            School("FSM Lisesi"),
            School("Galatasaray Lisesi")
        )
        val subjects = listOf(
            Subject("Programlamaya giris"),
            Subject("Nesneye Yonelik Progralama"),
            Subject("Room DB"),
            Subject("Firebase"),
            Subject("How to use Google")
        )
        val students = listOf(
            Student("Cengiz Kalem", 2, "FSM Lisesi"),
            Student("Kerem Can", 5, "Ataturk Lisesi"),
            Student("Halil Ak", 8, "FSM Lisesi"),
            Student("Ahmet Demir", 1, "FSM Lisesi"),
            Student("Murat Kekili", 2, "Galatasaray Lisesi")
        )
        val studentSubjectRelations = listOf(
            StudentSubjectCrossRef("Cengiz Kalem", "Programlamaya giris"),
            StudentSubjectCrossRef("Cengiz Kalem", "Nesneye Yonelik Progralama"),
            StudentSubjectCrossRef("Cengiz Kalem", "Room DB"),
            StudentSubjectCrossRef("Cengiz Kalem", "Firebase "),
            StudentSubjectCrossRef("Kerem Can", "Programlamaya giris"),
            StudentSubjectCrossRef("Halil Ak", "How to use Google"),
            StudentSubjectCrossRef("Ahmet Demir", "Firebase "),
            StudentSubjectCrossRef("Murat Kekili", "Nesneye Yonelik Progralama"),
            StudentSubjectCrossRef("Murat Kekili", "Programlamaya giris")
        )
        lifecycleScope.launch {
            directors.forEach { dao.insertDirector(it) }
            schools.forEach { dao.insertSchool(it) }
            subjects.forEach { dao.insertSubject(it) }
            students.forEach { dao.insertStudent(it) }
            studentSubjectRelations.forEach { dao.insertStudentSubjectCrossRef(it) }

            val schoolWithDirector = dao.getSchoolAndDirectorWithSchoolName("FSM Lisesi")

            val schoolWithStudents = dao.getSchoolWithStudents("FSM Lisesi")
        }

    }
}