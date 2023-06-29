package com.example.task

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var studentAdapter: StudentAdapter
    private lateinit var databaseRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        studentAdapter = StudentAdapter()
        recyclerView.adapter = studentAdapter


        val database: FirebaseDatabase = FirebaseDatabase.getInstance()
        databaseRef = database.reference.child("schools").push()


        val student1 = Student("Irakli1", "Gvelesiani", "1234567890123", "profile1.jpg", "irakli1.gvelesiani@gmail.com")
        val student2 = Student("Irakli2", "Gvelesiani2", "9876543210987", "profile2.jpg", "irakli2.gvelesiani@gmail.com")
        val student3 = Student("Irakli3", "Gvelesiani3", "4567890123456", "profile3.jpg", "irakli3.gvelesiani@gmail.com")

        val school = School(listOf(student1, student2, student3))
        databaseRef.setValue(school)


        databaseRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val students: MutableList<Student> = mutableListOf()
                for (studentSnapshot in dataSnapshot.child("students").children) {
                    val student = studentSnapshot.getValue(Student::class.java)
                    student?.let { students.add(it) }
                }
                studentAdapter.students = students
                studentAdapter.notifyDataSetChanged()
            }

            override fun onCancelled(databaseError: DatabaseError) {

            }
        })
    }
}