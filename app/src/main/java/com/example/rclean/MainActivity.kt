package com.example.rclean

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.rclean.adapter.HomePagedAdapter
import com.example.rclean.databinding.ActivityMainBinding
import com.example.rclean.entities.data.Director
import com.example.rclean.entities.data.School
import com.example.rclean.entities.data.Student
import com.example.rclean.entities.data.Subject
import com.example.rclean.entities.local.SchoolDatabase
import com.example.rclean.entities.relations.StudentSubjectCrossRef
import com.example.rclean.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var mAdapter: HomePagedAdapter
    private val viewModel: HomeViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupRecyclerView()
        loadingData()

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

    private fun setupRecyclerView() {

        mAdapter = HomePagedAdapter()

        binding.homeRv.apply {
            adapter = mAdapter
            layoutManager = StaggeredGridLayoutManager(
                2, StaggeredGridLayoutManager.VERTICAL
            )
            setHasFixedSize(true)
        }

    }

    private fun loadingData() {
        lifecycleScope.launch {
            viewModel.listData.collect { pagingData ->
                mAdapter.submitData(pagingData)
            }

        }
    }
}



