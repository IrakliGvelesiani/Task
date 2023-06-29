package com.example.task

data class School(val students: List<Student>)

data class Student(
    val firstName: String = "",
    val lastName: String = "",
    val personalNumber: String = "",
    val profilePicture: String = "",
    val email: String = ""
)