package com.example.rclean

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.rclean.entities.Director
import com.example.rclean.entities.School
import com.example.rclean.entities.Student
import com.example.rclean.entities.Subject
import com.example.rclean.entities.relations.StudentSubjectCrossRef

@Database(
    entities = [
        School::class,
        Student::class,
        Director::class,
        Subject::class,
        StudentSubjectCrossRef::class
    ],
    version = 1
)



abstract class SchoolDatabase : RoomDatabase() {
    abstract val  schoolDao : SchoolDao

    companion object {
        @Volatile
        private var INSTANCE: SchoolDatabase? = null

        fun getInstance(context: Context): SchoolDatabase {

            synchronized(this) {//lock this diğer threadlerden erişemeyecek.

                return  INSTANCE ?: Room.databaseBuilder(
                    context.applicationContext,
                    SchoolDatabase::class.java,
                    "school_db"
                ).build().also {
                    INSTANCE = it }

            }
        }
    }
}
